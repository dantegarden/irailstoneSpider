package com.dvt.irailstoneSpider.business.main.dto;

import java.util.ArrayList;
import java.util.List;

public class SerialCodeQueryResult {
	private int total; //页码总数
	private int page;  //当前页码
	private int records; //当前页多少条记录
	private String costtime; 
	private List<SerialCode> rows;
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
	public List<SerialCode> getRows() {
		return rows;
	}
	public void setRows(List<SerialCode> rows) {
		this.rows = rows;
	}
	public SerialCodeQueryResult(int total, int page, int records,
			String costtime, List<SerialCode> rows) {
		super();
		this.total = total;
		this.page = page;
		this.records = records;
		this.costtime = costtime;
		this.rows = rows;
	}
	public SerialCodeQueryResult() {
		super();
	}
	
	
}
