package co.edu.unbosque.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.Files;

@WebServlet(name = "DownloadImageServlet", value = "/DownloadImageServlet")
public class DownloadImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String photo= request.getParameter("var");
        String mime = Files.probeContentType(new File(getServletContext().getRealPath("./") +
                File.separator + "Images/"+photo).toPath());

        response.setContentType(mime);
        response.setHeader("Content-disposition", "attachment; filename="+photo);

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(getServletContext().getRealPath("./") +
                File.separator + "Images/"+photo);

        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();

    }
}
