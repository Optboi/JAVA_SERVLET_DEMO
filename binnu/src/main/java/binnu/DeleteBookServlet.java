package binnu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    private static final String query = "delete from BOOKDATA where BOOKNAME=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Get PrintWriter
        PrintWriter pw = res.getWriter();
        // Set content type
        res.setContentType("text/html");
        pw.println("<!DOCTYPE html>");
        pw.println("<html lang=\"en\">");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\">");
        pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        pw.println("<title>Delete Book</title>");
        pw.println("<style>");
        pw.println("body {");
        pw.println("    font-family: Arial, sans-serif;");
        pw.println("    background-color: #87CEEB; /* Sky Blue */");
        pw.println("}");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");
        
        // Retrieve session
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // User is logged in, allow deletion
            String bookName = req.getParameter("bookName");

            // Load JDBC driver
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException cnf) {
                cnf.printStackTrace();
            }
            
            // Generate the connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000", "root", "");
            		PreparedStatement ps = con.prepareStatement(query);) {
                ps.setString(1, bookName);
                int count = ps.executeUpdate();
                if (count == 1) {
                    pw.println("<h2>Record is Deleted Successfully</h2>");
                } else {
                    pw.println("<h2>!!! Record is not deleted !!!</h2>");
                }
            } catch (SQLException se) {
                se.printStackTrace();
                pw.println("<h1>" + se.getMessage() + "</h2>");
            } catch (Exception e) {
                e.printStackTrace();
                pw.println("<h1>" + e.getMessage() + "</h2>");
            }
        } else {
            // User is not logged in, redirect to login page
            res.sendRedirect("login.html");
        }
        
        // Home and Book List links styled as buttons
        pw.println("<a href='index.jsp' style='background-color: #4CAF50; /* Green button */\n" +
                "    border: none;\n" +
                "    color: white;\n" +
                "    padding: 15px 32px;\n" +
                "    text-align: center;\n" +
                "    text-decoration: none;\n" +
                "    display: inline-block;\n" +
                "    font-size: 16px;\n" +
                "    margin: 4px 2px;\n" +
                "    cursor: pointer;'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList' style='background-color: #4CAF50; /* Green button */\n" +
                "    border: none;\n" +
                "    color: white;\n" +
                "    padding: 15px 32px;\n" +
                "    text-align: center;\n" +
                "    text-decoration: none;\n" +
                "    display: inline-block;\n" +
                "    font-size: 16px;\n" +
                "    margin: 4px 2px;\n" +
                "    cursor: pointer;'>Book List</a>");
        
        pw.println("</body>");
        pw.println("</html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
