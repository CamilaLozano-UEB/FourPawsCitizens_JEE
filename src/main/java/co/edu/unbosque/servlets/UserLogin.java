package co.edu.unbosque.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "loginWithCookies", value = "/login-with-cookies")
public class UserLogin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        String name = request.getParameter("userName");
        try {

            PrintWriter out = response.getWriter();
            out.println("Hello " + name);
            out.println("<br />");
            //Creating two cookies
            Cookie c1 = new Cookie("userName", name);
            //Adding the cookies to response header
            response.addCookie(c1);

            out.println("<a href='welcome'>View Details</a>");
            out.close();

        } catch(Exception exp) {
            System.out.println(exp);
        }

    }

}
