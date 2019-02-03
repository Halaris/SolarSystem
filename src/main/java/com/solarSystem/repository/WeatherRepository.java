package com.solarSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solarSystem.model.SolarSystem;
import com.solarSystem.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	Weather findBySolarSystemAndDay(SolarSystem solarSystem, int day);

	List<Weather> findAllBySolarSystem(SolarSystem solarSystem);

}
