package com.Exception;

public class LeborMustException extends Exception{
	protected String msg;

	public LeborMustException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}

}
