package com.dvt.irailstoneSpider.business.main.dto;

public class CreateResult {
	private String Code;
	private String Message;
	private Boolean Success;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Boolean getSuccess() {
		return Success;
	}
	public void setSuccess(Boolean success) {
		Success = success;
	}
	public CreateResult(String code, String message, Boolean success) {
		super();
		Code = code;
		Message = message;
		Success = success;
	}
	public CreateResult() {
		super();
	}
	
}
