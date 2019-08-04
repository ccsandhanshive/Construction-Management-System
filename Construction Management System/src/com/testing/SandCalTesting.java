package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Exception.LessThanZeroException;
import com.serviceImplement.MaterialCal;

class SandCalTesting {

	@Test
	void test() throws LessThanZeroException {
	MaterialCal m=new MaterialCal();
	int output=m.sandCal(1);
	assertEquals(350,output);
	}

}
