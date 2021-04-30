package co.edu.unbosque.servlets;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.unbosque.services.UploadService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

@MultipartConfig
@WebServlet(name = "UserUpload", urlPatterns = { "/user-upload" })
public class UserUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 3,
				new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		String uploadPath = getServletContext().getRealPath("./") + File.separator + "Images";

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			uploadDir.mkdirs();

		List<FileItem> formItems;

		try {
			formItems = upload.parseRequest(request);

			String fileName = new File(formItems.get(0).getName()).getName();
			String filePath = uploadPath + File.separator + getNameAlfaNumeric()
					+ fileName.substring(fileName.lastIndexOf("."), fileName.length());

			formItems.get(0).write(new File(filePath));

			UploadService uploadService = new UploadService();
			Cookie[] cookies = request.getCookies();
			System.out.println(filePath);

			uploadService.storeRelation(getServletContext().getRealPath("./") + File.separator + "Relation",
					cookies[0].getValue(), new Date().toString(), filePath, formItems.get(1).getString("UTF-8"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getNameAlfaNumeric() {

		byte[] bytearray = new byte[256];
		String chain;
		StringBuffer namePhoto;
		String theAlphaNumericLower;
		String theAlphaNumericUpper;
		int i = 28;

		new Random().nextBytes(bytearray);
		chain = new String(bytearray, Charset.forName("UTF-8"));

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

		return namePhoto.toString();
	}

}