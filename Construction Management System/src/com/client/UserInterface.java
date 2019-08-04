package com.client;

import java.util.Scanner;
import java.util.Date;

import com.aspect.InterfaceProvider;
import com.serviceImplement.DatabaseImplements;
import com.serviceImplement.LoginImplement;
import com.serviceImplement.ServiceImpleamets;
import com.services.Contractor;
import com.services.Main;
import com.services.Supervisor;

import oracle.sql.DATE;

public class UserInterface {
	 Main m=new LoginImplement();
	 ServiceImpleamets s=new ServiceImpleamets();
	 DatabaseImplements d=new DatabaseImplements();
	 Scanner sc=new Scanner(System.in);
	 int press;
	 String date;
	 int days;
	static int labor[];
	 static int worker[];
	 int watchman;
	 String s1[];
	
 void changes(int userid,String password) 		//Changes for Input 
 {
		System.out.println("1.change in site\n2.change in date\n3.change in labor worker side\n4.change in watchman side \n5.back\nAny no for exit");
		int press2=sc.nextInt();
		
		if(press2==1)												//Change in site
			{press=site(userid, password,s1);
			changes(userid,password);
			}else if(press2==2)										//Change in date
			{
			System.out.println("Enter date (DD-MM-YYYY)");
			date=sc.nextLine();
			}else if(press2==3) //Change in labor worker side
			{
			 for(int i=0;i<days;i++)
			 {
					System.out.println("day "+i+" labor:");
					labor[i]=sc.nextInt();
					System.out.println("day "+i+" worker");
					worker[i]=sc.nextInt();
					System.out.println("\n");
			}
			 }else if(press2==4) 									//Change in watchman side
			{
			 System.out.println("Enter no of watchman");
			watchman=sc.nextInt();
			}else if(press2==5)										//back
			{
				mainSupervisor(userid,password);
			}else													//Exit 
			{
				System.exit(0);
			}
 }
	
	
	
	public  int site(int userid,String password,String s1[])	//For creating options for supervisor 
	{
		System.out.println("Enter site:");
		
		for(int i=1;i<s1.length+1;i++) {
			System.out.println(i+" "+s1[i-1]);
		}
		press=sc.nextInt();
		return press;
	}
	
	
	
	public void supervisor(int userid,String password)			//Supervisor submain Function
	{	System.out.println("Enter date (DD-MM-YYYY)");
		 date=sc.next();
		 
		 
		  s1=d.retriveSite(userid, password).split(",");		//for get supervision  sits from database 
		int press=site(userid,password,s1)-1;
		
		
		System.out.println("Enter no of days");
		days=sc.nextInt();
		labor=new int[days];
		worker=new int[days];
		
		for(int i=0;i<days;i++)
		{
			System.out.println("day "+i+" labor:");
			labor[i]=sc.nextInt();
			System.out.println("day "+i+" worker");
			worker[i]=sc.nextInt();
			System.out.println("\n");
		}
		
		System.out.println("Enter no of watchman");
		watchman=sc.nextInt();
		
		System.out.println("Enter cement count in bags");
		int cement=sc.nextInt();
		System.out.println("Enter sand count in trip");
		int sand=sc.nextInt();
		System.out.println("Enter brick count");
		int brick=sc.nextInt();
		
		
		System.out.println(s.dataEntry(s1[press],date,labor,worker,watchman,0));		//for show temporary data before save in database
		System.out.println(s.materialEntry(s1[press],date, cement, sand, brick,0));
		System.out.println("Save data \n1.yes \n2.no\n3.back");
		int press1=sc.nextInt();
		if(press1==1)
		{	s.dataEntry(s1[press],date,labor,worker,watchman,1);
		s.materialEntry(s1[press],date, cement, sand, brick,1);                       //Save in database
			mainSupervisor(userid, password);
		}else	if(press1==2)
		{
			changes(userid,password);
			mainSupervisor(userid,password);
		}else	if(press1==3) 
		{
		supervisor(userid,password);
		mainSupervisor(userid, password);
		}
		
		
		}
	
	
	
	
	
	
	
	public  void passChange(int userid,String password)					//Change in User password
	{
		System.out.println("Press 1 for change password\nAny other no for skip");
		int press=sc.nextInt();
		
		if(press==1) {
			System.out.println("Enter New Password");
			String newpass=sc.next();
			System.out.println("Are you sure\n1.yes 2.no 3.back");
			press=sc.nextInt();
				if(press==1) 
				{
						m.changePass(userid, password, newpass);
						passChange(userid, password);
				}
		}
	}
	
	public  void mainSupervisor(int userid,String password)			//Supervisor Main Operations 
	{
		System.out.println("1.Enter Data\n2.retrive data via site\n3.retrive data via site and date\n4.logout\nAny no for exit");
		int c=sc.nextInt();
		if(c==1)							//Enter data
		{
			supervisor(userid,password);
			mainSupervisor(userid, password);
		}else if(c==2) 						//Retrieve data site wise
		{
			System.out.println("Enter sitename");
			String site=sc.next();
			d.retriveData(site);
			mainSupervisor(userid, password);	
		}else if(c==3) 						//Retrieve data site and date wise 
		{
			System.out.println("Enter sitename:");
			String site=sc.next();
			System.out.println("Enter date");
			String date=sc.next();
			d.retriveData(site,date);
			mainSupervisor(userid, password);
		}else if(c==4)						//logout
		{
			login();
		}else 								//exit
		{
		System.exit(0);	
		}
		
	}
	public void contractor()						//Contractor operations
	{
		System.out.println("1.retrive data\n2.deletedata\n3.changes in rate\n4.User related changes\n5.logout");
		int press=sc.nextInt();
		if(press==1)					//Retrieve data
		{
			System.out.println("1.with site name\n2.with site name and date\n3.back\nAny other no for exit");
			int press1=sc.nextInt();
			if(press1==1) 			//site name wise
			{
				System.out.println("Enter Site name");
				String sitename=sc.next();
				d.retriveData(sitename);
				contractor();
			}else if(press1==2) 	//site name and date wise
			{
				System.out.println("Enter site name");
				String sitename=sc.next();
				System.out.println("Enter date");
				String date=sc.next();
				d.retriveData(sitename, date);
				contractor();
			}else	if(press1==3) 					//back
			{	press=0;
				contractor();
			}else 
			{
				System.exit(0);
			}
		}
		if(press==2) 							//delete data
		{
				System.out.println("1.with site\n2.site and date\n3.back\nAny other no for exit");
				int press2=sc.nextInt();
					if(press2==1) 			//delete specific site records
					{
							System.out.println("Enter site name");
							String sitename=sc.next();
							d.deleteData(sitename);
							contractor();
					}else if(press2==2)			//delete specific site with date
					{
					System.out.println("Enter site name");
					String sitename=sc.next();
					System.out.println("Enter date");
					String date=sc.next();
					d.deleteData(sitename, date);
					contractor();
					}else if(press2==3)			//back
					{
						contractor();
					}else 						//exit
					{
						System.exit(0);
					}
						
			
			
		}
		if(press==3) 						//Changes in rates
		{
			System.out.println("1.labor\n2.worker\n3.watchman\n4.cement\n5.sand\n6.brick\n7.back\nAny other no for exit");
			int ans=sc.nextInt();
			System.out.println("Enter rate");
			int rate=sc.nextInt();
			System.out.println("save rate\n1.yes\n2.no");
			int ans1=sc.nextInt();
			if(ans==1)				//labor
			{s.laborRateChange(rate,ans1);
			}else if(ans==2)		//worker
			{
				s.workerRateChange(rate,ans1);
			}else if(ans==3)		//watchman
			{	s.watchmanRateChange(rate,ans1);
			}else if(ans==4)		//cement
			{
				s.cementRateChange(rate,ans1);
			}else if(ans==5)		//sand
			{
				s.sandRateChange(rate,ans1);
			}else if(ans==6)		//brick
			{
				s.brickRateChange(rate,ans1);
			}else if(ans==7)		//back
			{
				contractor();
			}else					//exit 
			{
				System.exit(0);
			}
				contractor();
			
		}
		if(press==4)						//User related Changes
		{
			System.out.println("1.change in site\n2.detele user\n3.insert user\n4.display data\n5.back\nAny other no for exit");
			int sp=sc.nextInt();
			if(sp==1)			//Change in User site
			{
			System.out.println("Enter userid");
			int username=sc.nextInt();
			System.out.println("Enter new site name");
			String newsite=sc.next();
			System.out.println("Save data \n1.yes\n2.no");
			int n=sc.nextInt();
				s.supervisorSiteChange(username, newsite,n);
				contractor();
			}else if(sp==2) 				//Delete User
			{
				System.out.println("Enter userid");
				int userid=sc.nextInt();
				System.out.println("Are you sure?");
				int commit=sc.nextInt();
				s.deleteUser(userid, commit);
				contractor();
			}else if(sp==3)					//Insert User
			{
				System.out.println("Enter sitename");
				String sitename=sc.next();
				System.out.println("Enter role");
				String role=sc.next();
				System.out.println("Are you sure\n1.yes\n2.no");
				int commit=sc.nextInt();
				System.out.println(s.insertUser(sitename, role, commit));
				contractor();
			}else if(sp==4) 					//Display login data without password
			{
				System.out.println("1.all\n2.userid\n3.role based\n4.back\nAny no for exit");
				int r=sc.nextInt();
				if(r==1)				//Display all data
				{s.retriveUser(1);
				contractor();
				}else if(r==2)			//search by user id
				{
					System.out.println("Enter userid");
					int userid=sc.nextInt();
					s.retriveUser(userid,1);
					contractor();
					
				}else if(r==3)			//search by role
				{
					System.out.println("Enter role");
					String role=sc.next();
					s.retriveUser(role,1);
					contractor();
				}else if(r==4)				//Back
				{
					contractor();
				}
				else						//EXIT
				{
					System.exit(0);
				}
			}else if(sp==5)				//Back
			{
				contractor();
			}else 						//Exit
			{
			System.exit(0);	
			}
			
		
		
		
		}else if(press==5)				//logout
		{
			login();
		}else 							//Exit
		{
			System.exit(0);
		}
	}
	
	
	public  void login()						//User login 
	{	System.out.println("Enter 000 userid for exit");
		System.out.println("Enter userid:");
		int userid=sc.nextInt();
		if(userid==000)
			System.exit(0);
		
		System.out.println("Enter password:");
		String password=sc.next();
		
		String role=m.login(userid, password);			//login method in LoginImplemt return role of user
		System.out.println(role);
		if(role!=null)									//if not invalid user
		{	passChange(userid,password);
			if(role.equals("supervisor")) 				//If user is supervisor
			{
				mainSupervisor(userid, password);
			}else	if(role.equals("customer"))				//if user is customer
			{System.out.println("Your site data:-");
				d.retriveData(d.retriveSite(userid, password));
				System.out.println("End");
				login();
			}else	if(role.equals("contractor"))			//if user is contractor
			{
				contractor();
				login();
			}else											//If any other exit
			{
				System.exit(0);
			}
		}else 												//if invalid user
		{	System.out.println("Invalid user");
			login();
		}
	}	
		
	
public static void main(String args[]) {
	UserInterface u=InterfaceProvider.provideObject();
	System.out.println("1.Login\nAny other no for Exit");
	int ch=u.sc.nextInt();
	if(ch==1)
		u.login();
	System.exit(0);
	
}
}
