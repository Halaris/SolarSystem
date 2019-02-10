package com.solarSystem.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.solarSystem.model.Weather;
import com.solarSystem.service.SolarSystemServiceImpl;
import com.solarSystem.service.dto.WeatherDto;
import com.solarSystem.service.dto.WeatherReport;

@RestController
public class WeatherController {

	@Autowired
	SolarSystemServiceImpl solarSystemService;

	@GetMapping("/{day}")
	private WeatherDto getWeather(@PathVariable int day) {
		Weather weather = solarSystemService.getWeather(day);
		WeatherDto dto = new WeatherDto();
		dto.setRainIntensity(weather.getRainIntensity());
		dto.setWeather(weather.getWeather());
		return dto;
	}

	@GetMapping("/report/{from}/{to}")
	private WeatherReport getWeatherReport(@PathVariable int from, @PathVariable int to) {
		WeatherReport weather = solarSystemService.WeatherReport(from, to);
		return weather;
	}
}
