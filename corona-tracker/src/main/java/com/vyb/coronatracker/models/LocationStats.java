package com.vyb.coronatracker.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LocationStats implements Comparable<LocationStats>{
	
	private String state;
	private String country;
	private int totalCases;
	private int diffFromPrevious;
	private float increaseInPercentage;
	
	public float getIncreaseInPercentage() {
		return increaseInPercentage;
	}
	public void setIncreaseInPercentage(float increaseInPercentage) {
		BigDecimal bigDecimal = BigDecimal.valueOf(increaseInPercentage).setScale(2, RoundingMode.HALF_UP);
		this.increaseInPercentage = bigDecimal.floatValue();
	}
	public int getDiffFromPrevious() {
		return diffFromPrevious;
	}
	public void setDiffFromPrevious(int diffFromPrevious) {
		this.diffFromPrevious = diffFromPrevious;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", totalCases=" + totalCases + "]";
	}
	@Override
	public int compareTo(LocationStats input) {
		// TODO Auto-generated method stub
			return (this.totalCases < input.totalCases ) ? 1: (this.totalCases > input.totalCases) ?-1:0;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		LocationStats input = (LocationStats) obj;
		return this.getTotalCases()>input.getTotalCases();
	}
}
