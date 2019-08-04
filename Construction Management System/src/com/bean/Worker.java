package com.bean;

public class Worker {
private int workercount;
private int sal;
public Worker(int workercount, int sal) {
	this.workercount = workercount;
	this.sal = sal;
}
public int getWorkercount() {
	return workercount;
}
public void setWorkercount(int workercount) {
	this.workercount = workercount;
}
public int getSal() {
	return sal;
}
public void setSal(int sal) {
	this.sal = sal;
}
@Override
public String toString() {
	return "Worker [workercount=" + workercount + ", sal=" + sal + "]";
}

}
