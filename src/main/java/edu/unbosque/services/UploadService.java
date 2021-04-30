package edu.unbosque.services;

import java.io.IOException;

import javax.ejb.Singleton;

import edu.unbosque.jpa.repositories.UploadRepository;
import edu.unbosque.jpa.repositories.UploadRepositoryImp;

@Singleton
public class UploadService {

	UploadRepository uploadRepository;

	public boolean storeRelation(String contextFilePath, String userName, String uploadDate, String description,
			String imagePath) {

		uploadRepository = new UploadRepositoryImp();
		try {
			uploadRepository.store(contextFilePath, userName, uploadDate, description, imagePath);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}
}
