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
	// System.out.print("Successfully connected");
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
	
	public String readItems() {
		
		Connection con = connect();
		Statement preparedStmt = null;
		ResultSet results ;
		String output = ""; 

		
		if (con == null)
		{
		return "Error connecting to the database";
		}

		output = "<table border='1'><tr><th>Item Code</th>"
				 +"<th>Item Name</th><th>Item Price</th>"
				 + "<th>Item Description</th>"
				 + "<th>Update</th><th>Remove</th></tr>"; 
		
		String query = "select * from items";
		
		try {
			preparedStmt = con.createStatement();
			results = preparedStmt.executeQuery(query);
			
			while (results.next())
			 {
			 String itemID = Integer.toString(results.getInt("itemID"));
			 String itemCode = results.getString("itemCode");
			 String itemName = results.getString("itemName");
			 String itemPrice = Double.toString(results.getDouble("itemPrice"));
			 String itemDesc = results.getString("itemDesc");
			 
			 // Add a row into the html table
			 output += "<tr><td>" + itemCode + "</td>";
			 output += "<td>" + itemName + "</td>";
			 output += "<td>" + itemPrice + "</td>"; 
			 output += "<td>" + itemDesc + "</td>";
			 
			 // buttons
			 output += "<td><form method='post' action='Items.jsp'>"
			 		+ "<input name='btnChange' "
			 + " type='submit' value='Update'></td>"
			 + "<td>"
			 + "<input name='btnChange' "
			 + " type='submit' value='Remove'>"
			 + "<input name='itemID' type='hidden' "
			 + " value='" + itemID + "'>" + "</form></td></tr>";
			 }
			
			results.close();
			preparedStmt.close();
			con.close();
			
			 // Complete the html table
			 output += "</table>"; 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
		
	}
	
	public String deleteItem(String itemID) {
		
		String output = "";
		
		Connection con = connect();
		PreparedStatement stat = null;
		
		String query = "DELETE FROM `items` WHERE `items`.`itemID` = ?";
		
		try {
			
			stat = con.prepareStatement(query);
			stat.setString(1, itemID);
			stat.execute();
			
			output = "Deleted successfully";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 output = "Error while deleting";
			 System.err.println(e.getMessage());	
		}
		
		return output;
		
	}
	
	public String updateItem( String code, String name, String price, String desc , String itemID) {
		
		String output = "";
		
		Connection con = connect();
		PreparedStatement stat = null;
		
		String query =  "UPDATE `items` SET `itemCode` = ?, `itemName` = ?, `itemPrice` = ?, `itemDesc` = ? WHERE `items`.`itemID` = ?";
		try {
			stat = con.prepareStatement(query);
			
			stat.setString(1, code);
			stat.setString(2, name);
			stat.setDouble(3, Double.parseDouble(price));
			stat.setString(4, desc);
			System.out.println(itemID);
			stat.setString(5, itemID);
			stat.execute();

			output = "Updated successfully";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			output = "Error updating";
		}
		
		return output;
		
	}
	
	public String View(String itemID) {
		
		Connection con = connect();
		String output= "";
		
		String query = "select * from items where itemID = ?";
		
		try {
			PreparedStatement stat = con.prepareStatement(query);
			stat.setString(1, itemID);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				output = "<form method='post' action='Items.jsp'>"
						 +"Item code: <input name='itemCode' type='text' value = "+rs.getString("itemCode")+"><br>"
						 +"Item name: <input name='itemName' type='text' value = "+rs.getString("itemName")+"><br>"
						 +"Item price: <input name='itemPrice' type='text' value = "+rs.getDouble("itemPrice")+"><br>"
						 +"Item description: <input name='itemDesc' type='text' value = "+rs.getString("itemDesc")+"><br>"
						 +"<input type = 'hidden' name = 'itemID' value = "+itemID+">"
						 +"<input name='btnChange' type='submit' value='saveUpdate'>"
					+"</form>";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
		
	}

	


}
