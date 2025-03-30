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


public class LoginSbi extends HttpServlet {
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
			//PreparedStatement ps1=conn.prepareStatement("?,?,?");
			String s1=request.getParameter("branch");
			String s2=request.getParameter("anumber");
			long l1=Long.parseLong(s2);
			
			
			
		     PreparedStatement ps=conn.prepareStatement("select*from SBI where branch=? and anumber=?");
			ps.setString(1, s1);
			ps.setLong(2, l1);
			
			
			ResultSet set=ps.executeQuery();
			
			pw.println("<html><body bgcolor=yellow margin-top: 600px><center><h1>");
			if(set.next()) {
				String name=set.getString("cname");
				pw.println("Welcome "+name+"<br> ");
				pw.println("You have to Login Successfully.. <br>");
				pw.println("<a href=home1.html>Submit</a><br>");
				pw.println(" <a href=Home.html> Go back to home page</a>");
			}else {
				pw.println("Invalid A/C <br>");
				pw.println(" Your Account Is  Not Found");
				pw.println("<a href=sbireg.html> Regisration here</a><br>");
				pw.println(" <a href=Home.html> Go back to home page</a>");
			}
			pw.println("</h1></center></body></html>");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
