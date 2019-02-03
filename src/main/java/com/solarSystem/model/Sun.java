package com.solarSystem.model;

import javax.persistence.Entity;

import com.solarSystem.trigonometric.Vector;

@Entity
public class Sun {
	private double x0;
	private double y0;

	public Vector getPositionVec() {
		return new Vector(x0, y0);
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getY0() {
		return y0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

}
