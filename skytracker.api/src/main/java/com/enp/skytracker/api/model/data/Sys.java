package com.enp.skytracker.api.model.data;

public class Sys {
	
	private Integer id;
	private Integer type;
	private String country;
	private Integer sunrise;
	private Integer sunset;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getSunrise() {
		return sunrise;
	}

	public void setSunrise(Integer sunrise) {
		this.sunrise = sunrise;
	}

	public Integer getSunset() {
		return sunset;
	}

	public void setSunset(Integer sunset) {
		this.sunset = sunset;
	}

	@Override
	public String toString() {
		return "Sys [id=" + id + ", type=" + type + ", country=" + country + ", sunrise=" + sunrise + ", sunset="
				+ sunset + "]";
	}

}
