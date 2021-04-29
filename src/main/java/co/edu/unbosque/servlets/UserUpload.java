package co.edu.unbosque.servlets;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "UserUpload", urlPatterns = { "/user-upload" })
public class UserUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);
			String uploadPath = getServletContext().getRealPath("./") + File.separator + "images";
			System.out.println(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();

			try {
				List<FileItem> formItems = upload.parseRequest(request);

				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							System.out.println(storeFile);
							item.write(storeFile);
							System.out.println(storeFile.getAbsolutePath());

							System.out.println("Sirve");
							request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
						}
					}
				}
			} catch (Exception ex) {
				System.out.println("asdsadsadasddfghjkujyhtgrfedsa");
				request.setAttribute("message", "There was an error: " + ex.getMessage());
			}
		}
	}
}