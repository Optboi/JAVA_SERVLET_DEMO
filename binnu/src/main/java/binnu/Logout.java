package binnu;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Logout</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f8f9fa; padding: 20px; }");
        out.println("p { font-size: 18px; }");
        out.println("a { text-decoration: none; background-color: #007bff; color: #fff; padding: 10px 20px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        if (session != null) {
            session.invalidate();
            out.println("<p>You are logged out.</p>");
            out.println("<a href='index.jsp'>Go to Homepage</a>");
        } else {
            out.println("<p>You were not logged in.</p>");
            out.println("<a href='index.jsp'>Go to Homepage</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
