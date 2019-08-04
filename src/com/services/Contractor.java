package com.services;

public interface Contractor {
	int retriveData(String sitename);                    //retrive data using sitename
	int retriveData(String sitename,String date);		//retrive  data using sitename and date
	int deleteData(String sitename);			//delete  data using sitename
	int deleteData(String sitename,String date);		//delete data using sitename and date
	int laborRateChange(int rate);				//change in bebor rate 
	int workerRateChange(int rate);				//change in worker rate
	int cementRateChange(int rate);				//change in cement rate
	int sandRateChange(int rate);				//change in sand rate
	int brickRateChange(int rate);				//change in brick rate
	String insertUser(String sitename,String role);		//inert new user
	int supervisorSiteChange(int username,String newsite);	//change in supervisor supervision site
	int customerSiteChange(int username,String password,String sitename);	//change in customer site in this case customer password is must

}
