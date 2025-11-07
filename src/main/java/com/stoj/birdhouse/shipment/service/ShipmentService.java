package com.stoj.birdhouse.shipment.service;

import com.stoj.birdhouse.common.exception.NotFoundException;
import com.stoj.birdhouse.order.entity.CustomerOrder;
import com.stoj.birdhouse.order.entity.OrderStatus;
import com.stoj.birdhouse.order.repository.CustomerOrderRepository;
import com.stoj.birdhouse.shipment.dto.ShipmentCreateDto;
import com.stoj.birdhouse.shipment.dto.ShipmentDetailDto;
import com.stoj.birdhouse.shipment.dto.ShipmentSummaryDto;
import com.stoj.birdhouse.shipment.dto.ShipmentUpdateDto;
import com.stoj.birdhouse.shipment.entity.Shipment;
import com.stoj.birdhouse.shipment.mapper.ShipmentMapper;
import com.stoj.birdhouse.shipment.repository.ShipmentRepository;
import com.stoj.birdhouse.shipper.entity.Shipper;
import com.stoj.birdhouse.shipper.repository.ShipperRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepo;
    private final ShipmentMapper mapper;
    private final CustomerOrderRepository customerOrderRepo;
    private final ShipperRepository shipperRepo;

    @Autowired
    public ShipmentService(ShipmentMapper mapper, ShipmentRepository shipmentRepo, CustomerOrderRepository customerOrderRepo, ShipperRepository shipperRepo) {
        this.shipmentRepo = shipmentRepo;
        this.mapper = mapper;
        this.customerOrderRepo = customerOrderRepo;
        this.shipperRepo = shipperRepo;
    }

    // get all shipments
    @GetMapping("/shipments")
    public Page<ShipmentSummaryDto> getAllShipments(Pageable pageable) {
        return shipmentRepo.findAll(pageable).map(mapper::toSummaryDto);
    }

    // get a shipment
    public ShipmentDetailDto getShipment(Long id) {
        return shipmentRepo.findById(id).map(mapper::toDetailDto).orElseThrow(() -> new NotFoundException(String.format("Shipment not found with id: %d", id)));
    }

    // create a shipment
    @Transactional
    public ShipmentDetailDto createShipment(Long orderId, Long shipperId, ShipmentCreateDto createDto) {
        CustomerOrder order = customerOrderRepo.findById(orderId).orElseThrow(() -> new NotFoundException(String.format("Customer order not found with id: %d", orderId)));
        Shipper shipper = shipperRepo.findById(shipperId).orElseThrow(() -> new NotFoundException(String.format("Shipper not found with id: %d", shipperId)));

        // check order status
        if (!(order.getOrderStatus() == OrderStatus.PAID)) {
            throw new IllegalStateException(String.format("Cannot create shipment for order status: %s", order.getOrderStatus()));
        }

        // set order status to `shipped`
        order.setOrderStatus(OrderStatus.SHIPPED);

        // save order
        order = customerOrderRepo.save(order);

        // create shipment
        Shipment shipment = mapper.toEntity(createDto);
        shipment.setCustomerOrder(order);
        shipment.setShipper(shipper);

        shipment = shipmentRepo.save(shipment);
        return mapper.toDetailDto(shipment);
    }

    // update a shipment
    @Transactional
    public ShipmentDetailDto updateShipment(Long id, ShipmentUpdateDto updateDto) {
        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() -> new NotFoundException(String.format("Shipment not found with id: %d", id)));
        mapper.updateEntityFromDto(updateDto, shipment);
        return mapper.toDetailDto(shipment);
    }

    // delete a shipment
    @Transactional
    public void deleteShipment(Long id) {
        Shipment shipment = shipmentRepo.findById(id).orElseThrow(() -> new NotFoundException("Shipment not found"));
        shipmentRepo.delete(shipment);
    }

}
