package edu.unbosque.jpa.repositories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class UploadRepositoryImp implements UploadRepository {

	private final String FILE_PATH = "/imageRelation.txt";

	@Override
	public void store(String contextFilePath, String userName, String uploadDate, String description, String imagePath)
			throws IOException {
		// TODO Auto-generated method stub
		File dir = new File(contextFilePath);
		File f = new File(contextFilePath + FILE_PATH);
		System.out.println(f.getAbsolutePath());

		if (!dir.exists())
			dir.mkdirs();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "UTF-8"));
		bw.write(userName + "~" + uploadDate + "~" + description + "~" + imagePath + "\n");
		bw.close();

	}

}
