package com.solarSystem.trigonometric;

public class Triangle {
	private Vector v1;
	private Vector v2;
	private Vector v3;
	private Double perimeter;
	private Double area;

	private double error = 0.01;
	private Double s1;
	private Double s2;
	private Double s3;
	private Double t1;
	private Double t2;
	private Double t3;
	private boolean preCalculatedContain = false;

	private void preCalculateContains() {
		if (!preCalculatedContain) {
			preCalculatedContain = true;
			this.s1 = this.v3.getY() * this.v2.getX() - this.v3.getX() * this.v2.getY();

			this.s2 = (this.v2.getY() - this.v3.getY());

			this.s3 = (this.v3.getX() - this.v2.getX());

			this.t1 = this.v3.getX() * this.v1.getY() - this.v3.getY() * this.v1.getX();

			this.t2 = (this.v3.getY() - this.v1.getY());

			this.t3 = (this.v1.getX() - this.v3.getX());
		}
	}

	public Triangle(Vector v1, Vector v2, Vector v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}

	public boolean contains(Vector w) {
		this.preCalculateContains();
		double s = this.calculateHalfArea() * (this.s1 + this.s2 * w.getX() + this.s3 * w.getY());
		double t = this.calculateHalfArea() * (this.t1 + this.t2 * w.getX() + this.t3 * w.getY());
		return s > 0 && t > 0 && 1 - s - t > 0;
	}

	private double calculateHalfArea() {
		return 1 / (Math.abs(this.getArea()) * 2);
	}

	public Vector getV1() {
		return v1;
	}

	public void setV1(Vector v1) {
		this.v1 = v1;
	}

	public Vector getV2() {
		return v2;
	}

	public void setV2(Vector v2) {
		this.v2 = v2;
	}

	public Vector getV3() {
		return v3;
	}

	public void setV3(Vector v3) {
		this.v3 = v3;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public Double getPerimeter() {
		if (perimeter == null) {
			perimeter = this.caculatePerimeter();
		}
		return perimeter;
	}

	private Double caculatePerimeter() {
		return this.v1.dist(this.v2) + this.v2.dist(this.v3) + this.v3.dist(this.v1);
	}

	public Double getArea() {
		if (area == null) {
			area = this.caculateArea();
		}
		return area;
	}

	private Double caculateArea() {
		return 0.5 * (-this.v1.getY() * this.v2.getX() + this.v3.getY() * (-this.v1.getX() + this.v2.getX())
				+ this.v3.getX() * (this.v1.getY() - this.v2.getY()) + this.v1.getX() * this.v2.getY());
	}

}
