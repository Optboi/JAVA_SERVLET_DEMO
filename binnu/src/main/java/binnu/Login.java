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

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "SELECT * FROM `user` WHERE `name` LIKE ? AND `password` LIKE ?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String uname = req.getParameter("username");
        String pwd = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000",
                    "root", "");
                    PreparedStatement ps = con.prepareStatement(query);) {
                ps.setString(1, uname);
                ps.setString(2, pwd);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                    	HttpSession ssn= req.getSession();
            			ssn.setAttribute("user",uname );
            			RequestDispatcher rd= req.getRequestDispatcher("/index.jsp");
            			rd.forward(req, res);
                    } else {
                    	res.getWriter().println("<p>WRONG CREDENTIALS!!!!!</p>");
                		RequestDispatcher rd= req.getRequestDispatcher("/index.jsp");
                		rd.include(req, res);}
                }
            }
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        } catch (SQLException se) {
            throw new ServletException("Error executing SQL query.", se);
        } catch (Exception e) {
            throw new ServletException("Unexpected error.", e);
        }
    }
}
