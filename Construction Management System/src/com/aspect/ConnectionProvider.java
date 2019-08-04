package com.aspect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionProvider {
	static Connection con;
	static FileInputStream fis;
	static String s[]=new String[4];
	static Properties p;
	
	public static void getProperties()//Get Require Data From DBConfig.properties
	{
	try {
		fis=new FileInputStream(".//resources//DBConfig.properties");
		int x=0;
		p=new Properties();
		p.load(fis);
		
		
	s[0]=p.getProperty("DriverName");
	s[1]=p.getProperty("Url");
	s[2]=p.getProperty("UserName");
	s[3]=p.getProperty("Password");
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	
	public  static Connection provideConnection()  //Provide Connection
	{
		try {
			getProperties();
			Class.forName(s[0]);
			con=DriverManager.getConnection(s[1],s[2],s[3]);
			System.out.println("Connection Established");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return con;
	
	}
	
}
