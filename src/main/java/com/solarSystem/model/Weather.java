package com.solarSystem.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

import com.solarSystem.service.WeatherEnum;

@Entity
public class Weather implements Serializable {

	@EmbeddedId
	private WeatherId id;
	private double rainIntensity;
	@Enumerated(EnumType.STRING)
	private WeatherEnum weather;
	@ManyToOne
	@MapsId("system_id")
	private SolarSystem system;

	public Weather(long day, SolarSystem system) {
		super();
		this.id = new WeatherId(system.getId(), day);
		this.setSystem(system);
	}

	public Weather() {
		super();
	}

	@Transient
	public long getDay() {
		return this.id.getDay();
	}

	public void setDay(long day) {
		this.id.setDay(day);
	}

	public double getRainIntensity() {
		return rainIntensity;
	}

	public void setRainIntensity(double rainIntensity) {
		this.rainIntensity = rainIntensity;
	}

	public WeatherEnum getWeather() {
		return weather;
	}

	public void setWeather(WeatherEnum weather) {
		this.weather = weather;
	}

	public SolarSystem getSystem() {
		return system;
	}

	public void setSystem(SolarSystem system) {
		this.system = system;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id.getDay() ^ (id.getDay() >>> 32));
		result = prime * result + ((system == null) ? 0 : system.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (id.getDay() != other.id.getDay())
			return false;
		if (system == null) {
			if (other.system != null)
				return false;
		} else if (!system.equals(other.system))
			return false;
		return true;
	}

	public WeatherId getId() {
		return id;
	}

	public void setId(WeatherId id) {
		this.id = id;
	}
}
