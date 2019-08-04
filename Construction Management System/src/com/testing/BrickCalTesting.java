package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Exception.LessThanZeroException;
import com.serviceImplement.MaterialCal;

class BrickCalTesting {

	@Test
	void test() throws LessThanZeroException {
		MaterialCal m=new MaterialCal();
		int output=m.brickCal(30);
		assertEquals(90,output);
	}

}
