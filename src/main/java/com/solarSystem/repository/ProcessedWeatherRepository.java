package com.solarSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solarSystem.model.ProcessedWeather;
import com.solarSystem.model.SolarSystem;

public interface ProcessedWeatherRepository extends JpaRepository<ProcessedWeather, Integer> {
	ProcessedWeather findBySystem(SolarSystem solarSystem);
}
