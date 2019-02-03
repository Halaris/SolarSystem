package com.solarSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solarSystem.model.SolarSystem;

public interface SolarSystemRepository extends JpaRepository<SolarSystem, Integer> {
	SolarSystem findFirstByDefaultSystemTrue();

}
