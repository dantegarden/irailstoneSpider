package com.dvt.irailstoneSpider.business.main.dto;

public class Parameter {
	private String logic = "AND";
	private String paramName;
	private String paramValue;
	private String Operation = "Equal";
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	public Parameter(String logic, String paramName, String paramValue,
			String operation) {
		super();
		this.logic = logic;
		this.paramName = paramName;
		this.paramValue = paramValue;
		Operation = operation;
	}
	public Parameter() {
		super();
	}
	public Parameter(String paramName, String paramValue) {
		super();
		this.paramName = paramName;
		this.paramValue = paramValue;
	}
	public Parameter(String paramName, String paramValue, String logic) {
		super();
		this.logic = logic;
		this.paramName = paramName;
		this.paramValue = paramValue;
	}
	
}
