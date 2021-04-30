package edu.unbosque.jpa.repositories;

import java.io.IOException;

public interface UploadRepository {

	public void store(String contextFilePath, String userName, String uploadDate, String description, String imagePath)
			throws IOException;
}
