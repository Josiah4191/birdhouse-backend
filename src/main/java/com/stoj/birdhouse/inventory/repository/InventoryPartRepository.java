package com.stoj.birdhouse.inventory.repository;

import com.stoj.birdhouse.inventory.entity.InventoryPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryPartRepository extends JpaRepository<InventoryPart, Long> {}
