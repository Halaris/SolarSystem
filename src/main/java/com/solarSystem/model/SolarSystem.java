package com.solarSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.solarSystem.trigonometric.Line;
import com.solarSystem.trigonometric.Triangle;
import com.solarSystem.trigonometric.Vector;

@Entity
public class SolarSystem {
	@Id
	private int id;
	@OneToMany
	private List<Planet> planets;
	@OneToOne
	private Sun sun;
	private boolean defaultSystem;

	private Line l;
	private Triangle t;
	private List<Vector> updatedPoints = new ArrayList<>();

	public void calculatePosition(int days) {
		for (Planet p : planets) {
			updatedPoints.add(p.getPositionVec(days));
		}
	}

	public boolean planetsAligned() {
		l = new Line(updatedPoints.get(0), updatedPoints.get(1));
		return l.contains(updatedPoints.get(2));
	}

	public boolean sunAligned() {
		return l.contains(sun.getPositionVec());
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDefaultSystem() {
		return defaultSystem;
	}

	public void setDefaultSystem(boolean defaultSystem) {
		this.defaultSystem = defaultSystem;
	}

}
