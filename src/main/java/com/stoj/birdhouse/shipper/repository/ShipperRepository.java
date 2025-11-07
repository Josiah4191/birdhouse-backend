package com.stoj.birdhouse.shipper.repository;

import com.stoj.birdhouse.shipper.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {}
