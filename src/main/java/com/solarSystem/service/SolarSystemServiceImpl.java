package com.solarSystem.service;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solarSystem.model.SolarSystem;
import com.solarSystem.model.Weather;
import com.solarSystem.model.WeatherId;
import com.solarSystem.repository.ProcessedWeatherRepository;
import com.solarSystem.repository.SolarSystemRepository;
import com.solarSystem.repository.WeatherRepository;
import com.solarSystem.service.dto.Report;
import com.solarSystem.service.dto.ReportStatus;
import com.solarSystem.service.dto.WeatherReport;

@Service
public class SolarSystemServiceImpl {
	@Autowired
	public WeatherRepository weatherRepo;
	@Autowired
	public SolarSystemRepository solarSystemRepo;
	@Autowired
	public ProcessedWeatherRepository processRepo;
	@Autowired
	public WeatherUpdateJob weatherJob;
	@PersistenceContext
	public EntityManager em;

	public Weather getWeather(int day) {
		SolarSystem solarSystem = solarSystemRepo.findFirstByDefaultSystemTrue();
		Weather res;
		if (weatherJob.processWeather(solarSystem, day)) {
			res = weatherRepo.getOne(new WeatherId(solarSystem.getId(), day));
		} else {
			res = this.calculateWeather(solarSystem, day);
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
		return weatherRepo.findAllBySystem(solarSystem);
	}

	public WeatherReport WeatherReport(int from, int to) {
		SolarSystem solarSystem = solarSystemRepo.findFirstByDefaultSystemTrue();
		WeatherReport res = new WeatherReport();
		if (!weatherJob.processWeather(solarSystem, to)) {
			res.setStatus(ReportStatus.INPROCESS);
			return res;
		}

		Report report = new Report();
		StoredProcedureQuery spq = em.createStoredProcedureQuery("weather_report");
		spq.registerStoredProcedureParameter("dayFrom", Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter("dayTo", Integer.class, ParameterMode.IN);
		spq.setParameter("dayFrom", from);
		spq.setParameter("dayTo", to);
		spq.execute();
		List<Object[]> tuple = spq.getResultList();
		Object[] results = tuple.get(0);
		report.setDiasConSequia(((BigDecimal) results[4]).intValue());
		report.setMaximaIntensidadLluvia((Double) results[1]);
		report.setDiasOptimos(((BigDecimal) results[3]).intValue());
		report.setDiasLluviosos(((BigDecimal) results[0]).intValue());
		report.setDiasIndefinidos(((BigDecimal) results[2]).intValue());
		res.setStatus(ReportStatus.COMPLETED);
		res.setReport(report);
		return res;
		// em.getCriteriaBuilder().
	}
}
