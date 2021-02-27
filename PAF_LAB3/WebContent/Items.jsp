<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.Item"%>
    
    
    <%
	    if (request.getParameter("itemCode") != null){
	    	Item itemObj = new Item();
	    	String stsMsg = "";


	    	if(request.getParameter("btnChange").equalsIgnoreCase("save"))
	    	{
		    	 stsMsg = itemObj.insertItem( request.getParameter("itemCode"),
		    										request.getParameter("itemName"),
		    										request.getParameter("itemPrice"),
		    										request.getParameter("itemDesc"));
		    	session.setAttribute("statusMsg", stsMsg);
	    	}
			else if(request.getParameter("btnChange").equalsIgnoreCase("saveUpdate")){
	    		
				
			    stsMsg = itemObj.updateItem(   request.getParameter("itemCode"),
												request.getParameter("itemName"),
												request.getParameter("itemPrice"),
												request.getParameter("itemDesc") , 
												request.getParameter("itemID"));
		    	session.setAttribute("statusMsg", stsMsg);

	    	}
	    }
	    else if(request.getParameter("itemID") != null){
	    	String stsMsg = "";
		    Item itemObj2 = new Item();

	    	if(request.getParameter("btnChange").equalsIgnoreCase("remove"))
		    {
			    stsMsg = itemObj2.deleteItem(request.getParameter("itemID"));
			    
		    } 
	    	
	    
		    out.print(stsMsg);

    	}
    
		
		
	    
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>

	<h1>Items Management</h1>
	
	<%
	if(request.getParameter("itemID") != null){
		if(request.getParameter("btnChange").equalsIgnoreCase("update")){
			Item itemObj3 = new Item();
			out.print(itemObj3.View(request.getParameter("itemID")));
			
		}
		else{
			out.print("<form method='post' action='Items.jsp'>"
					 +"Item code: <input name='itemCode' type='text'><br>"
					 +"Item name: <input name='itemName' type='text'><br>"
					 +"Item price: <input name='itemPrice' type='text'><br>"
					 +"Item description: <input name='itemDesc' type='text'><br>"
					 +"<input name='btnChange' type='submit' value='Save'>"
				+"</form>");
		}
	}
	else{
		out.print("<form method='post' action='Items.jsp'>"
				 +"Item code: <input name='itemCode' type='text'><br>"
				 +"Item name: <input name='itemName' type='text'><br>"
				 +"Item price: <input name='itemPrice' type='text'><br>"
				 +"Item description: <input name='itemDesc' type='text'><br>"
				 +"<input name='btnChange' type='submit' value='Save'>"
			+"</form>");
		}
	
	
	
	
	%>
	
	
		<%
		 out.print(session.getAttribute("statusMsg"));
		%>
		
		<br>
	
		<%
		 Item itemObj = new Item();
		 out.print(itemObj.readItems());
		%>
		
	
	

</body>
</html>