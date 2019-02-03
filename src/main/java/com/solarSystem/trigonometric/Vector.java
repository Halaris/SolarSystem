package com.solarSystem.trigonometric;

public class Vector {

	private double x;
	private double y;

	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Generates a new vector that represents the subtraction from this vector with
	 * the parameter vector
	 * 
	 * @param v2
	 * @return
	 */
	public Vector sub(Vector v2) {
		return new Vector(this.getX() - v2.getX(), this.getY() - v2.getY());
	}

	public double dist(Vector v3) {
		return Math.sqrt(Math.pow((this.getX() - v3.getX()), 2) + Math.pow((this.getY() - v3.getY()), 2));
	}

}
