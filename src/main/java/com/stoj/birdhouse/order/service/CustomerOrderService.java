package com.stoj.birdhouse.order.service;

import com.stoj.birdhouse.common.exception.DuplicateOrderLineException;
import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.customer.entity.Customer;
import com.stoj.birdhouse.customer.repository.CustomerRepository;
import com.stoj.birdhouse.inventory.entity.InventoryPart;
import com.stoj.birdhouse.inventory.repository.InventoryPartRepository;
import com.stoj.birdhouse.order.dto.line.CustomerOrderLineCreateDto;
import com.stoj.birdhouse.order.dto.line.CustomerOrderLineResponseDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderCreateDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderResponseDto;
import com.stoj.birdhouse.order.dto.order.CustomerOrderUpdateDto;
import com.stoj.birdhouse.order.entity.CustomerOrder;
import com.stoj.birdhouse.order.entity.CustomerOrderLine;
import com.stoj.birdhouse.order.entity.OrderStatus;
import com.stoj.birdhouse.order.mapper.CustomerOrderLineMapper;
import com.stoj.birdhouse.order.mapper.CustomerOrderMapper;
import com.stoj.birdhouse.order.repository.CustomerOrderLineRepository;
import com.stoj.birdhouse.order.repository.CustomerOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepo;
    private final CustomerOrderLineRepository customerOrderLineRepo;
    private final CustomerRepository customerRepo;
    private final InventoryPartRepository inventoryPartRepo;
    private final CustomerOrderMapper orderMapper;
    private final CustomerOrderLineMapper orderLineMapper;

    public CustomerOrderService(CustomerOrderMapper orderMapper, CustomerOrderLineMapper orderLineMapper, CustomerOrderRepository customerOrderRepo, CustomerOrderLineRepository customerOrderLineRepo, CustomerRepository customerRepo, InventoryPartRepository inventoryPartRepo ) {
        this.customerOrderRepo = customerOrderRepo;
        this.customerOrderLineRepo = customerOrderLineRepo;
        this.customerRepo = customerRepo;
        this.inventoryPartRepo = inventoryPartRepo;
        this.orderMapper = orderMapper;
        this.orderLineMapper = orderLineMapper;
    }

    // get all customer orders
    public Page<CustomerOrderResponseDto> getAllCustomerOrders(Pageable pageable) {
        return customerOrderRepo.findAll(pageable).map(orderMapper::toDto);
    }

    // get customer order by id
    public CustomerOrderResponseDto getCustomerOrder(Long id) {
        return customerOrderRepo.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format("Customer order not found with id: %d", id)));
    }

    // get customer order by customer id
    public Page<CustomerOrderResponseDto> getCustomerOrderByCustomerId(Long id, Pageable pageable) {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundException(String.format("Customer not found with id: %d", id));
        }
        return customerOrderRepo.findByCustomerId(id, pageable).map(orderMapper::toDto);
    }

    // create customer order
    @Transactional
    public CustomerOrderResponseDto createCustomerOrder(Long customerId, CustomerOrderCreateDto createDto) {
        CustomerOrder customerOrder = orderMapper.toEntity(createDto);
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
        customerOrder.setCustomer(customer);
        customerOrder = customerOrderRepo.save(customerOrder);
        return orderMapper.toDto(customerOrder);
    }

    // update customer order
    @Transactional
    public CustomerOrderResponseDto updateCustomerOrder(Long id, CustomerOrderUpdateDto updateDto) {
        CustomerOrder customerOrder = customerOrderRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Customer order not found with id: %d", id)));
        customerOrder.setOrderStatus(updateDto.getOrderStatus());

        if (updateDto.getRequiredDate() != null) {
            customerOrder.setRequiredDate(updateDto.getRequiredDate());
        }
        customerOrder = customerOrderRepo.save(customerOrder);
        return orderMapper.toDto(customerOrder);
    }

    // delete customer order
    @Transactional
    public void deleteCustomerOrder(Long id) {
        CustomerOrder order = customerOrderRepo.findById(id).orElseThrow(() -> new NotFoundException("CustomerOrder not found"));
        if (order.getOrderStatus() == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Completed orders cannot be deleted.");
        }
        customerOrderRepo.deleteById(id);
    }

    // get order lines by order id
    public Page<CustomerOrderLineResponseDto> getOrderLines(Long orderId, Pageable pageable) {
        if (!customerOrderRepo.existsById(orderId)) {
            throw new NotFoundException(String.format("Customer order not found with id: %d", orderId));
        }
        return customerOrderLineRepo.findByCustomerOrderId(orderId, pageable).map(orderLineMapper::toDto);
    }

    // create an order line for an order id
    @Transactional
    public CustomerOrderLineResponseDto createOrderLine(Long orderId, CustomerOrderLineCreateDto createDto) {
        // get the order and part id
        Long partId = createDto.getInventoryPartId();

        // get the order
        CustomerOrder order = customerOrderRepo
                .findById(orderId)
                .orElseThrow(() -> new NotFoundException(String.format("Customer order not found with id: %d", orderId)));

        // get the order status
        OrderStatus status = order.getOrderStatus();

        // check if the status is NEW or PENDING
        if (!(status == OrderStatus.NEW || status == OrderStatus.PENDING)) {
            throw new IllegalStateException(String.format("Cannot create order line for order status: %s", status));
        }

        // check if the order line already exists for the order id and part id
        if (customerOrderLineRepo.existsByCustomerOrderIdAndInventoryPartId(orderId, partId)) {
            throw new DuplicateOrderLineException(
                    String.format("Duplicate order line for [order id: %d] [part id: %d]", orderId, partId));
        }

        // get the inventory part
        InventoryPart part = inventoryPartRepo
                .findById(partId)
                .orElseThrow(() -> new NotFoundException(String.format("Inventory part not found with id: %d", partId)));

        // create the order line
        CustomerOrderLine orderLine = orderLineMapper.toEntity(createDto);
        orderLine.setCustomerOrder(order);
        orderLine.setInventoryPart(part);
        orderLine.setUnitPrice(part.getStockPrice().setScale(2, RoundingMode.HALF_UP));

        // get the discount
        BigDecimal discount = Optional.ofNullable(orderLine.getDiscount()).orElse(BigDecimal.ZERO);
        // get the quantity * unit price
        BigDecimal max = orderLine.getUnitPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity()));

        // check if the discount exceeds the subtotal
        if (discount.compareTo(max) > 0) {
            throw new IllegalStateException("Discount exceeds line subtotal");
        }

        // set the discount
        orderLine.setDiscount(discount.setScale(2, RoundingMode.HALF_UP));
        // save the order line
        orderLine = customerOrderLineRepo.save(orderLine);

        return orderLineMapper.toDto(orderLine);
    }

    // delete an order line for an order id and order line id
    @Transactional
    public void deleteOrderLine(Long orderId, Long orderLineId) {
        // get the order line using order id and order line id
        CustomerOrderLine orderLine = customerOrderLineRepo
                .findByCustomerOrderIdAndId(orderId, orderLineId)
                .orElseThrow(() -> new NotFoundException(String.format("Order line not found for order id: %d", orderId)));

        // get the order status
        CustomerOrder order = orderLine.getCustomerOrder();
        OrderStatus status = order.getOrderStatus();

        // check if the status is NEW or PENDING
        if (!(status == OrderStatus.NEW || status == OrderStatus.PENDING)) {
            throw new IllegalStateException(String.format("Cannot delete order line for order status: %s", status));
        }
        customerOrderLineRepo.delete(orderLine);
    }


}
