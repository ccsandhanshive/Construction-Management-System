package com.bean;

public class Watchman {
	private int count;
	private int sal;
	public Watchman(int count, int sal) {
		super();
		this.count = count;
		this.sal = sal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return "Watchman [count=" + count + ", sal=" + sal + "]";
	}

	
}
