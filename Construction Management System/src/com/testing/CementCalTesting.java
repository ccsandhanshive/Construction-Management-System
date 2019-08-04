package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Exception.LessThanZeroException;
import com.serviceImplement.MaterialCal;

class CementCalTesting {

	@Test
	void test() throws LessThanZeroException {
		MaterialCal m=new MaterialCal();
		int output=m.cementCal(30);
		assertEquals(2700,output);
	}

}
