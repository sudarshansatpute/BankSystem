package bank.com;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheackBlance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection conn;
     
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","pass123");
			}catch(Exception e) {
				e.printStackTrace();
			}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		try {
			
			
			 
			String s1=request.getParameter("anumber");	
			Long l1=Long.parseLong(s1);
			
				PreparedStatement ps=conn.prepareStatement("select * from sbi where anumber=?");
		        ps.setLong(1, l1);
		        // ps.executeUpdate();
			ResultSet set=ps.executeQuery();
			pw.println("<html><body bgcolor=red><center><h1>");
			
			if(set.next()) {
			
				int number=set.getInt("amount");
				//long pin=set.getInt("anumber");
			     
					
				     pw.println("Youre A/C Bal :"+number+"  <br>");
				     pw.println(" <a href=Home.html> Go back to home page</a>");
				}else {
					pw.println("Invalid Pin Please Enter Correct PIN!");
					 pw.println(" <a href=Home.html> Go back to home page</a>");
				}
			
			 	 
			pw.println("</h1></center></body></html>");
		
			
		} catch (SQLException e) {
	       e.printStackTrace();
	       System.out.println(e.getMessage());
		}
	}

}
