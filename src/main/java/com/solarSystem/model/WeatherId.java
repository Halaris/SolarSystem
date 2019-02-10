package com.solarSystem.model;

import java.io.Serializable;

public class WeatherId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long day;
	private Integer system_id;

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public Integer getSystem_id() {
		return system_id;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (day ^ (day >>> 32));
		result = prime * result + ((system_id == null) ? 0 : system_id.hashCode());
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
		WeatherId other = (WeatherId) obj;
		if (day != other.day)
			return false;
		if (system_id == null) {
			if (other.system_id != null)
				return false;
		} else if (!system_id.equals(other.system_id))
			return false;
		return true;
	}

	public WeatherId(Integer system_id, long day) {
		super();
		this.day = day;
		this.system_id = system_id;
	}

	public WeatherId() {
		super();
	}
}