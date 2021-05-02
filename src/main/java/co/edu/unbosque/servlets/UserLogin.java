package co.edu.unbosque.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginWithCookies", value = "/login-with-cookies")
public class UserLogin extends HttpServlet {
    /**
     * constant for when a class has an extends
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method that creates cookies and adds them to store them.
     * It also redirects to index when the cookie has been saved.
     *
     * @param request,  get the values for cookies
     * @param response, manage the responses for the request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        String name = request.getParameter("userName");
        name=name.replace(",","").replace(";","").replace(" ", "");
        try {
            // Creating a cookie
            Cookie c1 = new Cookie("userName", name);
            // Adding the cookies to response header
            response.addCookie(c1);
            response.sendRedirect(request.getContextPath() + "/index.html");
        } catch (Exception exp) {
            System.out.println(exp);
        }

    }

}