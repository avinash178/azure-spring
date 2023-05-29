package com.eom.dto;

public class UserIdSequenceGenerator {
	
	int value=1;

	public int getValue() {
		return value++;
	}
}
