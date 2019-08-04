package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.serviceImplement.ServiceImpleamets;

class CommitTesting {

	@Test
	void test() {
		ServiceImpleamets si=new ServiceImpleamets();
		int output=si.brickRateChange(200,0);
		int output1=si.brickRateChange(200,1);
		
		assertEquals(0,output);
		assertEquals(1,output1);
		
	}
	

}
