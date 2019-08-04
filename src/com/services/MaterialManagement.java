package com.services;
import com.Exception.LessThanZeroException;;
public interface MaterialManagement {
	public int brickCal(int count) throws LessThanZeroException;	//calculation of brick amount
	public int sandCal(int count) throws LessThanZeroException;	//calculation of sand amount
	public int cementCal(int count) throws LessThanZeroException;	//calculation of cement amount
}
