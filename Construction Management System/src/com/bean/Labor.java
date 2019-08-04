package com.bean;

public class Labor {
private int laborcount;
private int sal;
public Labor(int laborcount, int sal) {
	super();
	this.laborcount = laborcount;
	this.sal = sal;
}
public int getLaborcount() {
	return laborcount;
}
public void setLaborcount(int laborcount) {
	this.laborcount = laborcount;
}
public int getSal() {
	return sal;
}
public void setSal(int sal) {
	this.sal = sal;
}
@Override
public String toString() {
	return "Labor [laborcount=" + laborcount + ", sal=" + sal + "]";
}

}
