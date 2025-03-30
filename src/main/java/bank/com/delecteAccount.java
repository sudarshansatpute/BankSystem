package bank.com;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class delecteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private Connection conn;
       int number;
       String s2;
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
			
			
			 
			String s1=request.getParameter("cname");
			 s2=request.getParameter("anumber");
			long l2=Long.parseLong(s2);
			
				PreparedStatement ps=conn.prepareStatement("delete from SBI where cname=? and anumber=?");
		     ps.setString(1, s1);
		    ps.setLong(2, l2);
		   
		 //  int del=ps.executeUpdate();
		   ResultSet rs=ps.executeQuery();
		   pw.println("<html><body bgcolor=red><center><h1>");
		   //String b1=String.valueOf(del);
			if(rs.next()) {
			 
			
		     pw.println("Deare "+s1+" Youre Account Deleted Successfully.. on <br>");
		    pw.println(LocalDateTime.now()+" Now You Can't Use SBI Facilites!<br>");
		    pw.println("Thank you..! State Bank Of India..<br>");
		    pw.println(" <a href=home1.html> Go back to home page</a>");
			
			
			}else {
				pw.println(" Account is Not Found.. ! Fill the Correct Information..!<br>");
				pw.println("Thank you..! State Bank Of India..");
				 pw.println(" <a href=home1.html> Go back to home page</a>");
			}
			pw.println("</h1></center></body></html>");
		
			 
		} catch (SQLException e) {
	       e.printStackTrace();
	       System.out.println(e.getMessage());
		}
	}

}
