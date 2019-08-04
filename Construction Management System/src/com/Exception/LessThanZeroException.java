package com.Exception;

public class LessThanZeroException extends Exception {
protected String msg;

public LessThanZeroException(String msg) {
	super();
	this.msg = msg;
}

@Override
public String toString() {
	return msg;
}
}
