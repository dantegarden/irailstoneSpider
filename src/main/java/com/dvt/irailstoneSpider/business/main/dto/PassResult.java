package com.dvt.irailstoneSpider.business.main.dto;

public class PassResult {
	private String state;
	private String msg;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PassResult(String state, String msg) {
		super();
		this.state = state;
		this.msg = msg;
	}
	
}
