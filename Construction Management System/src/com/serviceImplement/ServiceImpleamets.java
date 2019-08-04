package com.serviceImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Exception.LessThanZeroException;
import com.aspect.ConnectionProvider;

import com.bean.Labor;
import com.bean.Watchman;
import com.bean.Worker;
import com.services.Contractor;
import com.services.Customer;
import com.services.Supervisor;

public class ServiceImpleamets {
	Statement s;
	PreparedStatement p;
	int n;
	ResultSet rs;
	int userid;

	//if Commit ==1 then and then only data will be save on database after save data in database user cannot be modify it only Contractor can be deleted this record 
	//and only supervisor has authority to add data and modify before save	its applicable to all operations
		Connection con=ConnectionProvider.provideConnection();
		int c;
		DatabaseImplements d=new DatabaseImplements();
	public String dataEntry(String sitename, String date, int no_of_labor[], int no_of_worker[],int no_of_watchman,int commit) //Enter labor worker data
	{
		Labor l;
		Worker w;
		Watchman w1;
		int labor_amt;				//total amount of labor
		 int worker_amt;			//total amount of workers
		 int watchman_amt;			//total amount of watchman
		 int total_amt=0;				//total amount
		 int lcount=0;				//count total number of labors
		 int wcount=0;				//count total number of worker
		 
		for(int i=0;i<no_of_labor.length;i++) //counting labors
		{
			lcount+=no_of_labor[i];
		}
		
		for(int i=0;i<no_of_worker.length;i++)	//counting workers
		{
			wcount+=no_of_worker[i];
		}
		labor_amt=lcount*d.getRates("labor");			//get rate from rate table and multiply by there counts
		worker_amt=wcount*d.getRates("worker");
		watchman_amt=no_of_watchman*d.getRates("watchman");
		total_amt=labor_amt+worker_amt+watchman_amt;
		l=new Labor(lcount,labor_amt);
		w=new Worker(wcount,worker_amt);
		w1=new Watchman(no_of_watchman,watchman_amt);
		if(commit==1)									//if user wish to sava data in database
		d.dataEntry(sitename, date,lcount,labor_amt, wcount,worker_amt,no_of_watchman,watchman_amt,total_amt);
		
		return l+"\n"+w+"\n"+w1+"\ntotal amt="+total_amt;			//print all details for check

		
	}


	public int retriveData(String sitename,int commit)	//	retrieve data of site  
	{
		if(commit==1)
		c=d.retriveData(sitename);
		return c;
	}

	
	public int retriveData(String sitename, String date,int commit)		//retrieve data using site name and date 
	{
		if(commit==1)
		c=d.retriveData(sitename, date);
		return c;
	}

	
	public int deleteData(String sitename,int commit)					//delete all record of site
	{
		if(commit==1)
		c=d.deleteData(sitename);
		return c;
	}

	
	public int deleteData(String sitename, String date,int commit) 			//delete record of specific site and date
	{
		if(commit==1)
		c=d.deleteData(sitename, date);
		return c;
	}

	
	public int laborRateChange(int rate,int commit)						//Change in Labor rates 
	{
		if(commit==1)
		c=d.laborRateChange(rate);
		return c;
	}

	
	public int workerRateChange(int rate,int commit)					//Change in worker rates
	{
		if(commit==1)
		c=d.workerRateChange(rate);
		return c;
	}

	public int cementRateChange(int rate,int commit)					//Change in cement rates 
	{
		if(commit==1)
		c=d.cementRateChange(rate);
		return c;
	}

	public int sandRateChange(int rate,int commit)						//Change in sand rates 
	{
		if(commit==1)
		c=d.sandRateChange(rate);
		return c;
	}


	public int brickRateChange(int rate,int commit)						//Change in Brick rate Changes 
	{
		if(commit==1)
		c=d.brickRateChange(rate);
		return c;
	}


	public String insertUser(String sitename, String role,int commit)		//Insert New User 
	{
		if(commit==1)
		return d.insertUser(sitename, role);
		
		return null;
	}


	public int supervisorSiteChange(int username, String newsite,int commit)		//Supervisor site Change 
	{
		if(commit==1)
		c=d.supervisorSiteChange(username, newsite);
		return c;
	}


	public int customerSiteChange(int username, String password, String sitename,int commit)	// Customer Site change in this case customer password is must
	{
		if(commit==1)
		c=d.customerSiteChange(username, password, sitename);
		return c;
	}
	
	public int watchmanRateChange(int rate,int commit)							//Change in Watchman Rate Change 
	{
		if(commit==1)
			c=d.watchmanRateChange(rate);
		return c;
	}
	
	public int deleteUser(int userid,int commit)								//Delete User 
	{if(commit==1)
		c=d.deleteUser(userid);
	return c;
	}
	
	
	public String retriveUser(int commit)										//Retrieve all User Data
	{
		if(commit==1)
			d.retriveUser();
		return null;
	}
	
	
	public String retriveUser(int userid,int commit)							//Retrieve User data using userid
	{
		if(commit==1)
			d.retriveUser(userid);
		return null;
	}
	
	public String retriveUser(String role,int commit)							//Retrieve User data via there role 
	{
		if(commit==1)
			d.retriveUser(role);
		return null;
	}
	MaterialCal mc=new MaterialCal();
	public String materialEntry(String sitename,String date,int cement_count,int sand_count,int brick_count,int commit)
	{int cement_amt=0;
	int sand_amt=0;
	int brick_amt=0;
	 int total_amt=0;
		try {
		 cement_amt=mc.cementCal(cement_count);
		sand_amt=mc.sandCal(sand_count);
		  brick_amt=mc.brickCal(brick_count);
		  total_amt=cement_amt+sand_amt+brick_amt;
		 if(commit==1)
			 d.materialEntry(sitename, date, cement_count, cement_amt, sand_count, sand_amt, brick_count, brick_amt, total_amt);
	
	} catch (LessThanZeroException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	return "cement count= "+cement_count+" cement amount="+cement_amt+" \nsand count"+sand_amt+" sand amount"+sand_amt+"\nbrick count="+brick_count+" brick amount="+brick_amt+"\nTotal amount="+total_amt+"";
	 

		
	}
	
}
