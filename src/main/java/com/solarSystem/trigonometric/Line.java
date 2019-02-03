package com.solarSystem.trigonometric;

public class Line {
	private Vector u;
	private Vector v;
	private double error = 0.01;

	public Line(Vector v1, Vector v2) {
		this.u = v1;
		this.v = v1.sub(v2);
	}

	public boolean contains(Vector w) {
		double t1 = (w.getX() - u.getX()) / v.getX();
		double t2 = (w.getY() - u.getY()) / v.getY();
		double tFinal = Math.abs(t1 - t2);
		return tFinal < error;
	}

	public Vector getU() {
		return u;
	}

	public void setU(Vector u) {
		this.u = u;
	}

	public Vector getV() {
		return v;
	}

	public void setV(Vector v) {
		this.v = v;
	}

}
