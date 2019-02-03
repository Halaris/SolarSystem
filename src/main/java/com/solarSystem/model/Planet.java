package com.solarSystem.model;

import javax.persistence.Entity;

import com.solarSystem.trigonometric.Vector;

@Entity
public class Planet {
	private double x0;
	private double y0;
	private double r;
	private double theta0;
	private double speed;

	public Vector getPositionVec(int days) {
		double theta = this.updatedTheta(days);
		double x = x0 + r * Math.cos(Math.toRadians(theta));
		double y = y0 + r * Math.cos(Math.toRadians(theta));
		return new Vector(x, y);
	}

	private double updatedTheta(int days) {
		return this.theta0 + this.speed * days;
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

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getTheta() {
		return theta0;
	}

	public void setTheta(double theta) {
		this.theta0 = theta;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
