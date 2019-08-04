package com.serviceImplement;

import com.services.MaterialManagement;

import com.Exception.LessThanZeroException;

public class MaterialCal implements MaterialManagement {
	
	DatabaseImplements db=new DatabaseImplements();
	public int cementCal(int count) throws LessThanZeroException   //cement amout calculation count must be positive value
{
		if(count>=0) {
				count=count*db.getRates("cement");
		return count;
	}else{
		throw new LessThanZeroException("Cement count must be positive value");
	}}
	public int sandCal(int count) throws LessThanZeroException //sand amount calculation count must be positive value
{
		if(count>=0) {
		count = count*db.getRates("sand");
		return count;
	}else {
		throw new LessThanZeroException("Sand count must be positive value");
	}}
	public int brickCal(int count) throws LessThanZeroException   //brick amount caliculation count must be positive value
{
		if(count>=0) {
		count=count*db.getRates("brick");
		return count;}else {
			throw new LessThanZeroException("Brick count must be positive value");
		}
	}

}
