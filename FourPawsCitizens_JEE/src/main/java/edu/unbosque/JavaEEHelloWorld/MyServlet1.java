package edu.unbosque.JavaEEHelloWorld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "loginWithCookies", value = "/login-with-cookies")
public class MyServlet1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        try {

            PrintWriter out = response.getWriter();
            out.println("Hello " + name);
            out.println("<br />");
            out.println(" Your password is: " + password);
            out.println("<br />");

            //Creating two cookies
            Cookie c1 = new Cookie("userName", name);
            Cookie c2 = new Cookie("userPassword", password);

            //Adding the cookies to response header
            response.addCookie(c1);
            response.addCookie(c2);

            out.println("<a href='welcome'>View Details</a>");
            out.close();

        } catch(Exception exp) {
            System.out.println(exp);
        }

    }

}
