package co.edu.unbosque.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadImage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpg/png");
        response.setHeader("Content-disposition", "attachment; filename=sample.txt");

        try (InputStream input = request.getServletContext().getResourceAsStream("/WEB-INF/index.html");
             OutputStream output = response.getOutputStream()) {

            byte[] memory = new byte[1048];

            int memorynumber;
            while (0 < (memorynumber = input.read(memory))) {
                output.write(memory, 0, memorynumber);
            }
        }
    }
}