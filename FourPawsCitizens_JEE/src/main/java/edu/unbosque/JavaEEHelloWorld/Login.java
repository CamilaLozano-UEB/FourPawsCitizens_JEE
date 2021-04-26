package edu.unbosque.JavaEEHelloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("userName");
        String password = request.getParameter("userPassword");

        PrintWriter out = response.getWriter();
        if(name.equals("unbosque") && password.equals("123456"))
            out.println("Login Success...!");
        else
            out.println("Login Failed...!");

        out.close();

    }

}
