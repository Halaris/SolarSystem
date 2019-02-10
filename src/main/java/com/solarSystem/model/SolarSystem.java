package com.solarSystem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.solarSystem.trigonometric.Line;
import com.solarSystem.trigonometric.Triangle;
import com.solarSystem.trigonometric.Vector;

@Entity
public class SolarSystem implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToMany
	private List<Planet> planets;
	@OneToOne
	private Sun sun;
	private boolean defaultSystem;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((planets == null) ? 0 : planets.hashCode());
		result = prime * result + ((sun == null) ? 0 : sun.hashCode());
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
		SolarSystem other = (SolarSystem) obj;
		if (id != other.id)
			return false;
		if (planets == null) {
			if (other.planets != null)
				return false;
		} else if (!planets.equals(other.planets))
			return false;
		if (sun == null) {
			if (other.sun != null)
				return false;
		} else if (!sun.equals(other.sun))
			return false;
		return true;
	}

	@Transient
	private Line l;
	@Transient
	private Triangle t;
	@Transient
	private List<Vector> updatedPoints = new ArrayList<>();

	@Transient
	public void calculatePosition(int days) {
		updatedPoints.clear();
		for (Planet p : planets) {
			updatedPoints.add(p.getPositionVec(days));
		}
	}

	@Transient
	public boolean planetsAligned() {
		l = new Line(updatedPoints.get(0), updatedPoints.get(1));
		return l.contains(updatedPoints.get(2));
	}

	@Transient
	public boolean sunAligned() {
		return l.contains(sun.getPositionVec());
	}

	@Transient
	public boolean planetsContainSun() {
		t = new Triangle(updatedPoints.get(0), updatedPoints.get(1), updatedPoints.get(2));
		return t.contains(sun.getPositionVec());
	}

	public double getIntensity() {
		return t.getPerimeter();
	}

	public List<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public Sun getSun() {
		return sun;
	}

	public void setSun(Sun sun) {
		this.sun = sun;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isDefaultSystem() {
		return defaultSystem;
	}

	public void setDefaultSystem(boolean defaultSystem) {
		this.defaultSystem = defaultSystem;
	}

}
