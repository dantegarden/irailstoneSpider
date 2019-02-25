package com.dvt.irailstoneSpider.business.main.service;

import java.io.IOException;

public interface CallBackService {
	public void invokeOdooInPost(String backJson, Integer index, String message) throws IOException;
}
