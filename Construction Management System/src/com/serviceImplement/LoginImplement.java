package com.serviceImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aspect.ConnectionProvider;
import com.services.Main;


public class LoginImplement implements Main{
	Connection con=ConnectionProvider.provideConnection();
	PreparedStatement p;
	ResultSet rs;
	String s;
	int c;
	@Override
	public String login(int userid, String password)		//if userid and password present in login table return role else return null 
	{s=null;
		try {
			p=con.prepareStatement("select userid,password,role from login where userid=? and password= ?");
			p.setInt(1,userid);
			p.setString(2,password);
			rs=p.executeQuery();
			rs.next();
			s=rs.getString(3);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return s;
			}

	@Override
	public int changePass(int username, String password, String newpassword)	//For Change in password return updated row count 
	{
		try {
			p=con.prepareStatement("update  login set password=? where userid=? and password=? ");
			p.setString(1,newpassword);
			p.setInt(2,username);
			p.setString(3,password);
			c=p.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return c;
	}

}
