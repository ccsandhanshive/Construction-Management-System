package com.Exception;

public class WorkerMustException extends Exception{
protected String msg;

public WorkerMustException(String msg) {
	super();
	this.msg = msg;
}

@Override
public String toString() {
	return msg;
}

}
