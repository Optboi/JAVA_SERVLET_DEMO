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

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT * FROM BOOKDATA";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
       res.setContentType("text/html");
        String homeButton = "<a href='index.jsp'><button style='background-color: #4CAF50; /* Green */\n" +
                "    border: none;\n" +
                "    color: white;\n" +
                "    padding: 15px 32px;\n" +
                "    text-align: center;\n" +
                "    text-decoration: none;\n" +
                "    display: inline-block;\n" +
                "    font-size: 16px;\n" +
                "    margin: 4px 2px;\n" +
                "    cursor: pointer;'>Home</button></a>";


        // LOAD JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        // Generate the connection and fetch data
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000", "root", "");
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            // Start building the HTML content
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Book List</title>");
            pw.println("<style>");
            pw.println("body {background-color: #87CEEB;}"); // Sky blue background
            pw.println("table {border-collapse: collapse; width: 100%;}");
            pw.println("th, td {text-align: left; padding: 8px;}");
            pw.println("th {background-color: #f2f2f2;}");
            pw.println("tr:nth-child(even) {background-color: #f2f2f2;}");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");

            // Home Button
            pw.println(homeButton);

            // Table
            pw.println("<table border='1'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Author</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");

            // Table Content
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getInt(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("<td>" + rs.getFloat(5) + "</td>");
                pw.println("<td><a href='editScreen?ID=" + rs.getInt(1) + "'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?ID=" + rs.getInt(1) + "'>Delete</a></td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (SQLException se) {
            throw new ServletException("Error executing SQL query.", se);
        } catch (Exception e) {
            throw new ServletException("Unexpected error.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
