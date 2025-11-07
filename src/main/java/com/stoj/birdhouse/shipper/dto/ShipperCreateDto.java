package com.stoj.birdhouse.shipper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ShipperCreateDto {

    @NotBlank
    @Size(max = 50)
    private String shipperName;

    @Size(max = 50)
    private String contactName;

    @Size(max = 20)
    private String phone;

    @Email
    @Size(max = 254)
    private String email;

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
