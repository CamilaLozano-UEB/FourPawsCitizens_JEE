package edu.unbosque.JavaEEHelloWorld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "welcome", value = "/welcome")
public class MyServlet2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        try {

            Cookie[] cookies = request.getCookies();

            PrintWriter out = response.getWriter();
            out.println("Printing all cookies...");
            for (int i = 0 ; i < cookies.length ; i++) {
                out.println("<br />");
                out.println(cookies[i].getName() + ": " + cookies[i].getValue());
            }
            out.close();

        } catch(Exception exp) {
            System.out.println(exp);
        }

    }

}
