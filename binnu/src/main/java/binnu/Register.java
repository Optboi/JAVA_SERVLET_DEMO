package binnu;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/uregister")
public class Register extends HttpServlet{
	private static final String query = "INSERT INTO `user` (`name`, `sex`, `email`, `password`) VALUES (?,?,?,?)";
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
	{ 
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        
String name = req.getParameter("username");
        String sex = req.getParameter("sex");
        String email=req.getParameter("email");
        String password= req.getParameter("password");
        
	
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException cnf) {
	            cnf.printStackTrace();
	        }
		 try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000", "root", "");  
	             PreparedStatement ps = con.prepareStatement(query);) {
	            ps.setString(1, name);
	            ps.setString(2, sex);
	            ps.setString(3, email);
	            ps.setString(4, password);
	            
	            int count = ps.executeUpdate();
	            
	            if (count == 1) {
	                pw.println("<h2>Account Created Successfully</h2>");
	                pw.print("<a href='index.jsp'>home</a>");
	            } else {
	                pw.println("<h2>Account not Created Successfully</h2>");
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            pw.println("<h1>" + se.getMessage() + "</h2>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>" + e.getMessage() + "</h2>");
	        }	}
}
