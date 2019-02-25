package com.dvt.irailstoneSpider.commons.test.jdk8;

@FunctionalInterface
public interface FunctionalInterfaceTest {
	public String test1(String i);
	
	public static int minus(int a, int b){
		return a-b;
	}
	
	default int add(int a,int b){
		return a+b;
	}
}
