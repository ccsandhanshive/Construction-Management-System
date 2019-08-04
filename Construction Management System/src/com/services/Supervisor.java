package com.services;

public interface Supervisor {
	int dataEntry(String sitename,String date,int no_of_labor,int labor_amt,int no_of_worker,int worker_amt,int no_of_watchman, int watchman_amt,int total_amt );
	int retriveData(String sitename);
	int retriveData(String sitename,String date);

}
