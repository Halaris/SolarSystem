package com.solarSystem.service.dto;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQuery(name = "weather.report", procedureName = "weather_report", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "dayFrom", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "dayTo", type = Integer.class) })
public class Report {
	private int diasLluviosos;
	private double maximaIntensidadLluvia;
	private int diasOptimos;
	private int diasConSequia;
	private int diasIndefinidos;

	public int getDiasLluviosos() {
		return diasLluviosos;
	}

	public void setDiasLluviosos(int diasLluviosos) {
		this.diasLluviosos = diasLluviosos;
	}

	public double getMaximaIntensidadLluvia() {
		return maximaIntensidadLluvia;
	}

	public void setMaximaIntensidadLluvia(double maximaIntensidadLluvia) {
		this.maximaIntensidadLluvia = maximaIntensidadLluvia;
	}

	public int getDiasOptimos() {
		return diasOptimos;
	}

	public void setDiasOptimos(int diasOptimos) {
		this.diasOptimos = diasOptimos;
	}

	public int getDiasConSequia() {
		return diasConSequia;
	}

	public void setDiasConSequia(int diasConSequia) {
		this.diasConSequia = diasConSequia;
	}

	public int getDiasIndefinidos() {
		return diasIndefinidos;
	}

	public void setDiasIndefinidos(int diasIndefinidos) {
		this.diasIndefinidos = diasIndefinidos;
	}
}
