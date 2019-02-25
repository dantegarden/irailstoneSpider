package com.dvt.irailstoneSpider.business.main.dto;

import java.math.BigDecimal;

public class CreateSNParam {
	private String productCode;
	private String productName;
	private String makeNum;
	private String createTime;
	private String startNum;
	private String endNum;
	private String supplementInfo = "&nbsp;";
	private String remark = "&nbsp;";
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMakeNum() {
		return makeNum;
	}
	public void setMakeNum(String makeNum) {
		this.makeNum = makeNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
		this.computeEndNum();
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.computeEndNum();
	}
	public String getSupplementInfo() {
		return supplementInfo;
	}
	public void setSupplementInfo(String supplementInfo) {
		this.supplementInfo = supplementInfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public CreateSNParam(String productCode, String productName, String makeNum) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.makeNum = makeNum;
	}
	public CreateSNParam(String productCode, String productName,
			String makeNum, String createTime, String startNum, String endNum,
			String supplementInfo, String remark) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.makeNum = makeNum;
		this.createTime = createTime;
		this.startNum = startNum;
		this.endNum = endNum;
		this.supplementInfo = supplementInfo;
		this.remark = remark;
		this.computeEndNum();
	}
	public CreateSNParam() {
		super();
	}
	public CreateSNParam(String productCode, String productName,
			String makeNum, String createTime, String startNum,
			String supplementInfo, String remark) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.makeNum = makeNum;
		this.createTime = createTime;
		this.startNum = startNum;
		this.supplementInfo = supplementInfo;
		this.remark = remark;
		this.computeEndNum();
	}
	public CreateSNParam(String productCode, String productName,
			String makeNum, String createTime, String startNum) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.makeNum = makeNum;
		this.createTime = createTime;
		this.startNum = startNum;
		this.computeEndNum();
	}
	
	private void computeEndNum(){
		BigDecimal bd = new BigDecimal(this.startNum);
		this.endNum = bd.add(new BigDecimal(this.makeNum)).subtract(new BigDecimal("1")).toPlainString();
	}
}
