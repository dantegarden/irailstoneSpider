package com.dvt.irailstoneSpider.business.main.service;

import com.dvt.irailstoneSpider.business.main.dto.SerialNumberResult;

public interface IrailStoneService {
	
	/**获得序列号*/
	public SerialNumberResult getSerialNumber(String ewmJson) throws Exception;
	public SerialNumberResult cancelSerialNumber(String ewmJson) throws Exception;
}
