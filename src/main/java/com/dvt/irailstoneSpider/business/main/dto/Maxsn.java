package com.dvt.irailstoneSpider.business.main.dto;

public class Maxsn {
	private String maxsn;
	private String supplement;
	public String getMaxsn() {
		return maxsn;
	}
	public void setMaxsn(String maxsn) {
		this.maxsn = maxsn;
	}
	public String getSupplement() {
		return supplement;
	}
	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}
	public Maxsn(String maxsn, String supplement) {
		super();
		this.maxsn = maxsn;
		this.supplement = supplement;
	}
	public Maxsn() {
		super();
	}
	
	
}
