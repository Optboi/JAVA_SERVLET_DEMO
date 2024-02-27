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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String query = "INSERT INTO BOOKDATA(BOOKNAME,BOOKAUTHOR,BOOKEDITION,BOOKPRICE) VALUES(?,?,?,?)";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        
        // Get book information from the request parameters
        String bookName = req.getParameter("bookName");
        String bookAuthor = req.getParameter("bookAuthor");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        
        final String uname = "root";
        final String pwd = "";
        final String url = "jdbc:mysql://localhost:3306/book";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection(url, uname, pwd);
             PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, bookName);
            ps.setString(2, bookAuthor);
            ps.setString(3, bookEdition);
            ps.setFloat(4, bookPrice);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.print("<!DOCTYPE html>\r\n"
                        + "<html lang=\"en\">\r\n"
                        + "<head>\r\n"
                        + "    <meta charset=\"UTF-8\">\r\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                        + "    <title>Record Is Registered Successfully</title>\r\n"
                        + "    <style>\r\n"
                        + "        body {\r\n"
                        + "            background-color: skyblue;\r\n"
                        + "            margin: 0;\r\n"
                        + "            padding: 0;\r\n"
                        + "        }\r\n"
                        + "        .container {\r\n"
                        + "            text-align: center;\r\n"
                        + "            padding: 50px;\r\n"
                        + "            font-size: 24px;\r\n"
                        + "            color: white;\r\n"
                        + "        }\r\n"
                        + "        .btn {\r\n"
                        + "            background-color: green;\r\n"
                        + "            color: white;\r\n"
                        + "            padding: 10px 20px;\r\n"
                        + "            text-decoration: none;\r\n"
                        + "            border-radius: 5px;\r\n"
                        + "            margin: 5px;\r\n"
                        + "        }\r\n"
                        + "    </style>\r\n"
                        + "</head>\r\n"
                        + "<body>\r\n"
                        + "<div class='container'>\r\n"
                        + "<h2 class='text-success'>Record Is Registered Successfully</h2>\r\n"
                        + "<a href='index.jsp' class='btn'>Home</a>\r\n"
                        + "<a href='bookList' class='btn'>Book List</a>\r\n"
                        + "</div>\r\n"
                        + "</body>\r\n"
                        + "</html>");
            } else {
                pw.print("<!DOCTYPE html>\r\n"
                        + "<html lang=\"en\">\r\n"
                        + "<head>\r\n"
                        + "    <meta charset=\"UTF-8\">\r\n"
                        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                        + "    <title>Record not Registered Successfully</title>\r\n"
                        + "    <style>\r\n"
                        + "        body {\r\n"
                        + "            background-color: skyblue;\r\n"
                        + "            margin: 0;\r\n"
                        + "            padding: 0;\r\n"
                        + "        }\r\n"
                        + "        .container {\r\n"
                        + "            text-align: center;\r\n"
                        + "            padding: 50px;\r\n"
                        + "            font-size: 24px;\r\n"
                        + "            color: white;\r\n"
                        + "        }\r\n"
                        + "        .btn {\r\n"
                        + "            background-color: green;\r\n"
                        + "            color: white;\r\n"
                        + "            padding: 10px 20px;\r\n"
                        + "            text-decoration: none;\r\n"
                        + "            border-radius: 5px;\r\n"
                        + "            margin: 5px;\r\n"
                        + "        }\r\n"
                        + "    </style>\r\n"
                        + "</head>\r\n"
                        + "<body>\r\n"
                        + "<div class='container'>\r\n"
                        + "<h2 class='text-danger'>Record not Registered Successfully</h2>\r\n"
                        + "<a href='index.html' class='btn'>Home</a>\r\n"
                        + "<a href='bookList' class='btn'>Book List</a>\r\n"
                        + "</div>\r\n"
                        + "</body>\r\n"
                        + "</html>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
