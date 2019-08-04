package com.serviceImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aspect.ConnectionProvider;
import com.services.Contractor;
import com.services.Customer;
import com.services.Supervisor;

public class DatabaseImplements  implements Contractor,Customer,Supervisor {
Statement s;
PreparedStatement p;
int n;
ResultSet rs;
int userid;

	
	Connection con=ConnectionProvider.provideConnection();
	int c;
	@Override
	public int dataEntry(String sitename,String date,int no_of_labor,int labor_amt,int no_of_worker,int worker_amt,int no_of_watchman, int watchman_amt,int total_amt)  //Enter laber worker data 
	{
		
		try {
			p=con.prepareStatement("insert into "+sitename+" values(?,?,?,?,?,?,?,?)");
			p.setString(1,date);
			p.setInt(2,no_of_labor);
			p.setInt(3,labor_amt);
			p.setInt(4,no_of_worker);
			p.setInt(5,worker_amt);
			p.setInt(6,no_of_watchman);
			p.setInt(7,watchman_amt);
			p.setInt(8,total_amt);
			c=p.executeUpdate();
			System.out.println("Data inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			boolean b=createSiteTable(sitename);//if table not exit then create table table name is site name
			dataEntry(sitename,date,no_of_labor,labor_amt,no_of_worker,worker_amt,no_of_watchman,watchman_amt,total_amt); //Try to enter data again
			//e.printStackTrace();
		}
	return c;
	}

	@Override
	public int retriveData(String sitename)					//search and display data using site name
	{
		try {
			p=con.prepareStatement("select w.date_,w.no_of_labor,w.laber_amt,w.no_of_worker,w.worker_amt,w.no_of_watchman,w.watchman_amt,w1.cement_count,w1.cement_amt,w1.sand_count,w1.sand_amt,w1.brick_count,w1.brick_amt,w.total_amt+w1.total_amount as total_amount from "+sitename+" w,"+sitename+"Material w1 where w.date_=w1.date_");
		rs=p.executeQuery();
		System.out.println("  date   no_of_labor laber_amt no_of_worker worker_amt no_of_watchman watchman_amt cement_count cement_amt sand_count sand_amt brick_count brick_amt total_amount");
		while(rs.next()) {
			System.out.println(rs.getString(1)+"      "+rs.getInt(2)+"     "+rs.getInt(3)+"      "+rs.getInt(4)+"         "+rs.getInt(5)+"       "+rs.getInt(6)+"         "+rs.getInt(7)+"          "+rs.getInt(8)+"         "+rs.getInt(9)+"          "+rs.getInt(10)+"         "+rs.getInt(11)+"         "+rs.getInt(12)+"         "+rs.getInt(13)+"        "+rs.getInt(14));
			
		}
			 
		} catch (SQLException e) {
			createSiteTable(sitename);		//if table not exit create new table and again try to retrieve
			retriveData(sitename);
		return 1;
		}
		return 0;
	}

	@Override
	public int retriveData(String sitename, String date) {
		try {
			p=con.prepareStatement("select\r\n" + 
					" w.date_,\r\n" + 
					"w.no_of_labor,\r\n" + 
					"w.laber_amt,\r\n" + 
					"w.no_of_worker,\r\n" + 
					"w.worker_amt,\r\n" + 
					"w.no_of_watchman,\r\n" + 
					"w.watchman_amt,\r\n" + 
					"w1.cement_count,\r\n" + 
					"w1.cement_amt,\r\n" + 
					"w1.sand_count,\r\n" + 
					"w1.sand_amt,\r\n" + 
					"w1.brick_count,\r\n" + 
					"w1.brick_amt,\r\n" + 
					"w.total_amt+w1.total_amount as total_amount \r\n" + 
					"from "+sitename+" w,"+sitename+"Material w1 where w.date_=w1.date_ and w.date_='"+date+"'");
			rs=p.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getInt(2)+" "+rs.getInt(3)+" "+rs.getInt(4)+" "+rs.getInt(5)+" "+rs.getInt(6)+" "+rs.getInt(7)+" "+rs.getInt(8)+" "+rs.getInt(9)+" "+rs.getInt(10)+" "+rs.getInt(11)+" "+rs.getInt(12)+" "+rs.getInt(13)+" "+rs.getInt(14));
				c=rs.getRow();
			}
				 
			} catch (SQLException e) {
				createSiteTable(sitename);		//if table not exit create new table and again try to retrieve
				retriveData(sitename,date);
				//e.printStackTrace();
			}
			return c;
	}

	@Override
	public int deleteData(String sitename)		//delete all records of specific site 
	{
		try {
			s=con.createStatement();
			s.executeUpdate("truncate table "+sitename);
			} catch (SQLException e) {
				System.out.println("Record not exit");// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
	}

	@Override
	public int deleteData(String sitename, String date)		//delete all record of specific site and date
	{
		try {
			s=con.createStatement();
		    c=s.executeUpdate("delete from "+sitename+" where date_ = '"+date+"'");
		    c=s.executeUpdate("delete from "+sitename+"Material  where date_='"+date+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			return c;
	}

	@Override
	public int laborRateChange(int rate)					//For change in labor changes
	{
		try {
			p=con.prepareStatement("update rate set labor=? ");
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public int workerRateChange(int rate)				//For change in worker rates 
	{
		try {
			p=con.prepareStatement("update rate set worker=? ");	
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public int cementRateChange(int rate)				//for Change in cement rate 
	{
		try {
			p=con.prepareStatement("update rate set cement=? ");	
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public int sandRateChange(int rate)					//for Change in sand rates  
	{
		try {
			p=con.prepareStatement("update rate set sand=? ");	
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public int brickRateChange(int rate) 					//Change in Brick rates
	{
		try {
			p=con.prepareStatement("update rate set brick=? where brick>0");	
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public String insertUser(String sitename, String role)		//For Insert new user 
	{
		try {
			userid=retriveData()+1;								//For generate user id
			p=con.prepareStatement("insert into login values(?,?,?,?)");
			
			p.setInt(1,userid);
			p.setString(2,sitename+"/"+role+"/"+userid);
			p.setString(3,role);
			p.setString(4,sitename);
			c=p.executeUpdate();
			 System.out.println("new user inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return "userid="+userid+" pass= "+sitename+"/"+role+"/"+userid;			//return userid and password for note
	}

	@Override
	public int supervisorSiteChange(int username, String newsite) 				//If change supervisor's supervision site 
	{
		try {
			p=con.prepareStatement("update login set sitename=? where userid=?");	
			p.setString(1,newsite);
			p.setInt(2,username);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}

	@Override
	public int customerSiteChange(int username, String password, String sitename) 			//For Change in customer site Changes in this case Customer Password is must
	{
		try {
			p=con.prepareStatement("update login set sitename=? where userid=? and password=?" );	
			p.setString(1,sitename);
			p.setInt(2,userid);
			p.setString(3,password);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}
	public int retriveData()						//Get old maximum user id for generate new user id which is unique
	{
		try {
			p=con.prepareStatement("select max(userid) from login");
		rs=p.executeQuery();
		rs.next();
		c=rs.getInt(1);
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public int getRates(String ratenm)					//get rates from rate table for calculations 
	{
		try {
			p=con.prepareStatement("select * from rate");
			rs=p.executeQuery();
			rs.next();
			c=rs.getInt(ratenm);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}

	public String retriveSite(int userid,String pass) 		//For retrieve User side for table related operations
	{
		String s=null;
		try {
			p=con.prepareStatement("select sitename from login where userid=? and password=?");
			p.setInt(1,userid);
			p.setString(2,pass);
			rs=p.executeQuery();
			rs.next();
		 s=rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return s;
	}
	
	public int watchmanRateChange(int rate)				//For Change in Watchman rates 
	{
		try {
			p=con.prepareStatement("update rate set watchman=? ");
			p.setInt(1,rate);
			c=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return c;
	}
	
	public int deleteUser(int userid)						//for Delete User 
	{
		try {
			s=con.createStatement();
		    c=s.executeUpdate("delete from login where userid ="+userid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			return c;
	}
	
	public String retriveUser()									//For Retrieve all User data From login
	{
		String s=null;
		try {
			p=con.prepareStatement("select userid,role,sitename from login ");
			rs=p.executeQuery();
			while(rs.next()) {
				System.out.println("userid= "+rs.getInt(1)+"\nrole = "+rs.getString(2)+"\nsitename = "+rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return s;
	}
	
	
	public String retriveUser(int userid)					//For Retrieve all User data from login using userid  
	{
		String s=null;
		try {
			p=con.prepareStatement("select userid,role,sitename from login where userid=?");
			p.setInt(1,userid);
			rs=p.executeQuery();
			while(rs.next()) {
				System.out.println("userid= "+rs.getInt(1)+"\nrole = "+rs.getString(2)+"\nsitename = "+rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return s;
	}
	
	
	public String retriveUser(String role)						//For Retrieve all User data from login using role 
	{
		String s=null;
		try {
			p=con.prepareStatement("select userid,role,sitename from login where role=?");
			p.setInt(1,userid);
			rs=p.executeQuery();
			while(rs.next()) {
				System.out.println("userid= "+rs.getInt(1)+"\nrole = "+rs.getString(2)+"\nsitename = "+rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return s;
	}
	public boolean createSiteTable(String sitename) 		//For Creating New site Table
	{boolean b=false;
		try {
			p=con.prepareStatement("create table "+sitename+"(date_ varchar2(30),no_of_labor int,laber_amt int,no_of_worker int,worker_amt int,no_of_watchman int,watchman_amt int,total_amt int)");
			b=p.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	public int materialEntry(String sitename,String date,int cement_count,int cement_amt,int sand_count,int sand_amt,int brick_count, int brick_amt,int total_amt)  //Enter laber worker data 
	{
	 c=0;
	System.out.println("In materialEntry "+sitename+"Material");
		
		try {
			p=con.prepareStatement("insert into "+sitename+"Material values(?,?,?,?,?,?,?,?)");
			p.setString(1,date);
			p.setInt(2,cement_count);
			p.setInt(3,cement_amt);
			p.setInt(4,sand_count);
			p.setInt(5,sand_amt);
			p.setInt(6,brick_count);
			p.setInt(7,brick_amt);
			p.setInt(8,total_amt);
			c=p.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		boolean b=createMaterialTable(sitename+"Material");//if table not exit then create table table name is site name
			
		materialEntry(sitename,date,cement_count,cement_amt,sand_count,sand_amt,brick_count,brick_amt,total_amt); //Try to enter data again
		
			//e.printStackTrace();
		}
	return c;
	}
	public boolean createMaterialTable(String sitename)    //For create material table for perticular site if not exit 
{
		System.out.println("In create "+sitename);
		boolean b=false;
		try {
			p=con.prepareStatement("create table "+sitename+"(date_ varchar2(30),cement_count int,cement_amt int,sand_count int,sand_amt int,brick_count int,brick_amt int,total_amount int)");
			b=p.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

}
