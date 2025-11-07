package com.stoj.birdhouse.shipment.repository;

import com.stoj.birdhouse.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
