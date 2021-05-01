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
        String name = "NyZxU8ZxLwXoIy8hRkChZs44HnCe.png";
        String path = this.getServletContext().getRealPath("/Images/" + name);
        String fileName = path.substring(path.lastIndexOf("\\") + 1);
        response.setContentType("image/jpg/png");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        try(InputStream in =request.getServletContext().getResourceAsStream("/Images/" + name);
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1048];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) >0) {
                System.out.print(numBytesRead+"No esta entrando");
                out.write(buffer, 0, numBytesRead);
            }
        }

    }
}