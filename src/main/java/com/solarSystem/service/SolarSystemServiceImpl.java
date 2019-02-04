package com.solarSystem.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.solarSystem.model.SolarSystem;
import com.solarSystem.model.Weather;
import com.solarSystem.repository.SolarSystemRepository;
import com.solarSystem.repository.WeatherRepository;

public class SolarSystemServiceImpl {
	@Autowired
	private WeatherRepository weatherRepo;
	@Autowired
	private SolarSystemRepository solarSystemRepo;
	@PersistenceContext
	EntityManager em;

	public Weather getWeather(int day) {
		SolarSystem solarSystem = solarSystemRepo.findFirstByDefaultSystemTrue();
		Weather res = weatherRepo.findBySolarSystemAndDay(solarSystem, day);
		if (res == null) {
			res = this.calculateWeather(solarSystem, day);
			weatherRepo.save(res);
		}
		return res;
	}

	public Weather calculateWeather(SolarSystem solarSystem, int days) {

		Weather res = new Weather(days, solarSystem);
		solarSystem.calculatePosition(days);
		if (solarSystem.planetsAligned()) {
			if (solarSystem.sunAligned()) {
				res.setWeather(WeatherEnum.SEQUIA);
			} else {
				res.setWeather(WeatherEnum.CONDICIONOPTIMA);
			}
		} else {
			if (solarSystem.planetsContainSun()) {
				res.setWeather(WeatherEnum.LLUVIA);
				res.setRainIntensity(solarSystem.getIntensity());
			} else {
				res.setWeather(WeatherEnum.INDEFINIDO);
			}
		}
		return res;
	}

	public List<Weather> getAllWeathers(SolarSystem solarSystem) {
		return weatherRepo.findAllBySolarSystem(solarSystem);
	}

	public void getReportOfWeather(SolarSystem solarSystem) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> query =cb.createTupleQuery();
		Root<Weather> root = query.from(Weather.class);
		//em.getCriteriaBuilder().
	}
}
