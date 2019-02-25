package com.dvt.irailstoneSpider.commons.listener.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dvt.irailstoneSpider.commons.listener.AbstractResourcePreloaderListener;

public class ResourcePreloaderListener extends AbstractResourcePreloaderListener{
	
	private static final Logger logger = LoggerFactory.getLogger(ResourcePreloaderListener.class);
	
	@Override
	protected void preload(ApplicationContext applicationContext) {
		System.out.println("~~~~perload~~~~");
	}

	private void initDataDict(ApplicationContext applicationContext) {
	}


}
