package com.solarSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solarSystem.model.SolarSystem;
import com.solarSystem.model.Weather;
import com.solarSystem.model.WeatherId;

public interface WeatherRepository extends JpaRepository<Weather, WeatherId> {
	List<Weather> findAllBySystem(SolarSystem solarSystem);

}
