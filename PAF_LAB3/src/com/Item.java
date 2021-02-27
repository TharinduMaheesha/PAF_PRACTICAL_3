package com;

import java.sql.*;

public class Item {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/pafpractical3",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		
		Connection con = connect();
		PreparedStatement preparedStmt;
		String output = ""; 

		
		if (con == null)
		{
		return "Error connecting to the database";
		}

		String query = " insert into items (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
				 + " values (?, ?, ?, ?, ?)";
				try {
					preparedStmt = con.prepareStatement(query);
					
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, code);
					preparedStmt.setString(3, name);
					preparedStmt.setDouble(4, Double.parseDouble(price));
					preparedStmt.setString(5, desc);
					
					preparedStmt.execute();
					con.close();
					preparedStmt.close();
					
					output = "Inserted successfully"; 

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 output = "Error while inserting";
					 System.err.println(e.getMessage()); 	
				}
				
				return output;
				
				


	}


}
