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

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final String query = "update BOOKDATA set BOOKNAME=?,BOOKAUTHOR=?,BOOKEDITION=?,BOOKPRICE=? where ID=?";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // User is logged in, allow access to edit functionality
            // Set background color to sky blue
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>Edit Record</title>");
            pw.println("<style>");
            pw.println("body { background-color: #87CEEB; }"); // Sky blue background
            pw.println("a.button {");
            pw.println("  background-color: #4CAF50; /* Green */");
            pw.println("  border: none;");
            pw.println("  color: white;");
            pw.println("  padding: 15px 32px;");
            pw.println("  text-align: center;");
            pw.println("  text-decoration: none;");
            pw.println("  display: inline-block;");
            pw.println("  font-size: 16px;");
            pw.println("  margin: 4px 2px;");
            pw.println("  cursor: pointer;");
            pw.println("}");
            pw.println("</style>");
            pw.println("</head>");
            pw.println("<body>");

            int id = Integer.parseInt(req.getParameter("ID"));
            String bookAuthor =req.getParameter("bookAuthor");
            String bookName = req.getParameter("bookName");
            String bookEdition = req.getParameter("bookEdition");
            float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException cnf) {
                cnf.printStackTrace();
            }

            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book?connectTimeout=50000", "root", "");
                 PreparedStatement ps = con.prepareStatement(query);) {
                ps.setString(1, bookName);
                ps.setString(2, bookAuthor);
                ps.setString(3, bookEdition);
                ps.setFloat(4, bookPrice);
                ps.setInt(5, id);
                int count = ps.executeUpdate();
                if (count == 1) {
                    pw.println("<h2>Record is Edited Successfully</h2>");
                } else {
                    pw.println("<h2>!!!!!!!!Record is not Edited !!!!!!!</h2>");
                }
            } catch (SQLException se) {
                se.printStackTrace();
                pw.println("<h1>" + se.getMessage() + "</h2>");
            } catch (Exception e) {
                e.printStackTrace();
                pw.println("<h1>" + e.getMessage() + "</h2>");
            }
            
            // Home and Book List links styled as buttons
            pw.println("<a href='index.jsp' class='button'>Home</a>");
            pw.println("<br>");
            pw.println("<a href='bookList' class='button'>Book List</a>");

            pw.println("</body>");
            pw.println("</html>");
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
