package com.stoj.birdhouse.shipment.dto;

import com.stoj.birdhouse.order.dto.order.CustomerOrderSummaryDto;
import com.stoj.birdhouse.shipment.entity.ShipmentStatus;
import com.stoj.birdhouse.shipper.dto.ShipperSummaryDto;

public class ShipmentDetailDto {

    private Long id;
    private ShipmentStatus shipmentStatus;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private ShipperSummaryDto shipper;
    private CustomerOrderSummaryDto customerOrder;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public ShipperSummaryDto getShipper() {
        return shipper;
    }

    public void setShipper(ShipperSummaryDto shipper) {
        this.shipper = shipper;
    }

    public CustomerOrderSummaryDto getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderSummaryDto customerOrder) {
        this.customerOrder = customerOrder;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
