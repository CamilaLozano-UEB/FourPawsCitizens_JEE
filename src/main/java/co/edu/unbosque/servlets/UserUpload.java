package co.edu.unbosque.servlets;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.services.ImageService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;

@MultipartConfig
@WebServlet(name = "UserUpload", urlPatterns = {"/user-upload"})
public class UserUpload extends HttpServlet {
    /**
     * constant for when a class has an extends
     */
    private static final long serialVersionUID = 1L;
    /**
     *The method save the image and description with the corresponding user and calculate the date.
     * Finally pass the information for write and redirect to listTable.jsp
     *
     * @param request,  element that brings all cookies
     * @param response, element that redirect to listTable.jsp
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 3,
                new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        String uploadPath = getServletContext().getRealPath("./") + File.separator + "Images";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdirs();

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            String fileName = new File(formItems.get(0).getName()).getName();
            String alpha = getNameAlfaNumeric(fileName);

            formItems.get(0).write(new File(uploadPath + File.separator + alpha));

            ImageService imageService = new ImageService();
            String contextFilePath = getServletContext().getRealPath("./") + File.separator + "Relation";
            String userNameCookie = "";
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("userName")) {
                    userNameCookie = cookies[i].getValue();
                }
            }

            imageService.storeRelation(contextFilePath, userNameCookie, new Date().toString(), formItems.get(1).getString("UTF-8"),
                    alpha);

            response.sendRedirect(request.getContextPath() + "/listTable.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /****
     *This method make the name alfanumeric of the image with 28 characters in upper and lower case
     *
     * @param fileName, it's the file name where will be the image
     * @return namePhoto.toString() + fileName.substring(fileName.lastIndexOf(".")), it's the new name of the image with type of file
     * ****/

    private String getNameAlfaNumeric(String fileName) {

        byte[] bytearray = new byte[256];
        String chain;
        StringBuffer namePhoto;
        String theAlphaNumericLower;
        String theAlphaNumericUpper;
        int i = 28;

        new Random().nextBytes(bytearray);
        chain = new String(bytearray, StandardCharsets.UTF_8);

        namePhoto = new StringBuffer();

        theAlphaNumericLower = chain.replaceAll("[^A-Z0-9]", "").toLowerCase();
        theAlphaNumericUpper = chain.replaceAll("[^A-Z0-9]", "");

        for (int m = 0; m < theAlphaNumericLower.length(); m++) {

            if (Character.isLetter(theAlphaNumericLower.charAt(m)) && (i > 0)
                    || Character.isDigit(theAlphaNumericLower.charAt(m)) && (i > 0)
                    || Character.isLetter(theAlphaNumericUpper.charAt(m)) && (i > 0)
                    || Character.isDigit(theAlphaNumericUpper.charAt(m)) && (i > 0)) {
                if (i % 2 == 0) {
                    namePhoto.append(theAlphaNumericUpper.charAt(m));
                } else {
                    namePhoto.append(theAlphaNumericLower.charAt(m));
                }
                i--;
            }
        }

        return namePhoto.toString() + fileName.substring(fileName.lastIndexOf("."));
    }

}