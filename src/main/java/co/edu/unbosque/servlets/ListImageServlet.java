package co.edu.unbosque.servlets;

import co.edu.unbosque.pojos.Image;
import co.edu.unbosque.services.ImageService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ListImageServlet", value = "/ListImageServlet")
public class ListImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ImageService imageService = new ImageService();
        String userNameCookie = "";
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userName")) {
                userNameCookie = cookies[i].getValue();
            }
        }
        List<Image> images = imageService.listImages(getServletContext().getRealPath("./") +
                File.separator + "Relation", userNameCookie);
        String imagesJsonString = new Gson().toJson(images);

        PrintWriter out = response.getWriter();
        out.print(imagesJsonString);
        out.flush();
    }
}
