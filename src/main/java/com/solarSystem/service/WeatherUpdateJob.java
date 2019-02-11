package com.solarSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solarSystem.model.ProcessedWeather;
import com.solarSystem.model.SolarSystem;
import com.solarSystem.model.Weather;
import com.solarSystem.repository.ProcessedWeatherRepository;
import com.solarSystem.repository.SolarSystemRepository;
import com.solarSystem.repository.WeatherRepository;

@Service
public class WeatherUpdateJob {
	@Autowired
	private WeatherRepository weatherRepo;
	@Autowired
	private SolarSystemRepository solarSystemRepo;
	@Autowired
	private ProcessedWeatherRepository processRepo;
	@Autowired
	private SolarSystemServiceImpl solarSystemService;

	private ExecutorService executorQueue = Executors.newSingleThreadExecutor();
	@PersistenceContext
	private EntityManager em;

	public boolean processWeather(SolarSystem solarSystem, int day) {
		boolean isProcessed = false;
		ProcessedWeather pw = processRepo.findBySystem(solarSystem);
		if (pw == null) {
			pw = new ProcessedWeather();
			pw.setDaysProcessing(-1);
			pw.setSystem(solarSystem);
			pw.setProcessedDays(-1);
		} else {
			isProcessed = pw.getProcessedDays() >= day;
		}
		if (isProcessed) {
			return isProcessed;
		} else if (pw == null || pw.getDaysProcessing() < day) {
			int from = pw != null ? pw.getDaysProcessing() : 0;
			executorQueue.submit(() -> this.processWeather2(solarSystem, from, day));
			pw.setDaysProcessing(day);
			processRepo.save(pw);
		}
		return isProcessed;
	}

	private void processWeather2(SolarSystem solarSystem, int from, int to) {
		List<Weather> weathers = new ArrayList<Weather>();
		while (to > from) {
			while (weathers.size() < 1000) {
				weathers.add(solarSystemService.calculateWeather(solarSystem, from));
				from++;
			}
			weatherRepo.saveAll(weathers);
			updateProcessData(solarSystem, from, to);
			weathers.clear();
		}

	}

	private void updateProcessData(SolarSystem solarSystem, int from, int to) {
		ProcessedWeather pw = processRepo.findBySystem(solarSystem);
		if (pw == null) {
			pw = new ProcessedWeather();
			pw.setSystem(solarSystem);
			pw.setDaysProcessing(from);
		}
		pw.setProcessedDays(to);
		processRepo.save(pw);
	}
}