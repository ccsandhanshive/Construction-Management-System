package com.services;

public interface Supervisor {
	int dataEntry(String sitename,String date,int no_of_labor,int labor_amt,int no_of_worker,int worker_amt,int no_of_watchman, int watchman_amt,int total_amt );//insert lebor, worker //and watchaman count 
	int retriveData(String sitename);	// retrive data of perticuler site
	int retriveData(String sitename,String date);	//retrive data of perticuler site using date

}
