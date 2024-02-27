package binnu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT BOOKNAME,BOOKAUTHOR,BOOKEDITION,BOOKPRICE FROM BOOKDATA where ID=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("ID"));

        // Retrieve the session
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // User is logged in, allow access to the edit screen
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException cnf) {
                cnf.printStackTrace();
            }

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                rs.next();

                // Form with styled CSS
                pw.println("<!DOCTYPE html>");
                pw.println("<html>");
                pw.println("<head>");
                pw.println("<title>Edit Book</title>");
                pw.println("<style>");
                pw.println("body { background-color: #87CEEB; }");
                pw.println("form { margin: 50px auto; padding: 20px; width: 50%; background-color: #FFFFFF; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3); }");
                pw.println("input[type='text'], input[type='submit'], input[type='reset'] { width: 100%; padding: 12px; margin: 6px 0; display: inline-block; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }");
                pw.println("input[type='submit'], input[type='reset'] { background-color: #4CAF50; color: white; border: none; cursor: pointer; }");
                pw.println("</style>");
                pw.println("</head>");
                pw.println("<body>");
                pw.println("<button style='background-color: #4CAF50; /* Green */\n" +
                        "border: none;\n" +
                        "color: white;\n" +
                        "padding: 15px 32px;\n" +
                        "text-align: center;\n" +
                        "text-decoration: none;\n" +
                        "display: inline-block;\n" +
                        "font-size: 16px;\n" +
                        "margin: 4px 2px;\n" +
                        "cursor: pointer;'><a href='index.jsp' style='color: white; text-decoration: none;'>Home</a></button>");

                pw.println("<form action='editurl?ID=" + id + "' method='post'>");
                pw.println("<table align='center'>");
                pw.println("<tr>");
                pw.println("<td>Book Name</td>");
                pw.println("<td><input type='text' name='bookName' value='" + rs.getString(1) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Book Author</td>");
                pw.println("<td><input type='text' name='bookAuthor' value='" + rs.getString(2) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Book Edition</td>");
                pw.println("<td><input type='text' name='bookEdition' value='" + rs.getString(3) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td>Book Price</td>");
                pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(4) + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='Cancel'></td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("</form>");
                pw.println("</body>");
                pw.println("</html>");
            } catch (SQLException se) {
                throw new ServletException("Error executing SQL query.", se);
            } catch (Exception e) {
                throw new ServletException("Unexpected error.", e);
            }
        } else {
            // User is not logged in, redirect to login page
            res.sendRedirect("login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
