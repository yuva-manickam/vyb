package com.vyb.coronatracker.models;

public class LocationStats {
	
	private String state;
	private String country;
	private int totalCases;
	private int diffFromPrevious;
	
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
	


}
