package com.dvt.irailstoneSpider.business.main.dto;

import java.util.List;

public class SerialGroupQueryResult {
	private int total; //页码总数
	private int page;  //当前页码
	private int records; //当前页多少条记录
	private String costtime; 
	private List<SerialCodeGroup> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public String getCosttime() {
		return costtime;
	}
	public void setCosttime(String costtime) {
		this.costtime = costtime;
	}
	public List<SerialCodeGroup> getRows() {
		return rows;
	}
	public void setRows(List<SerialCodeGroup> rows) {
		this.rows = rows;
	}
	public SerialGroupQueryResult() {
		super();
	}
	
	
}	
