package com.services;
import com.Exception.LessThanZeroException;;
public interface MaterialManagement {
	public int brickCal(int count) throws LessThanZeroException;
	public int sandCal(int count) throws LessThanZeroException;
	public int cementCal(int count) throws LessThanZeroException;
}
