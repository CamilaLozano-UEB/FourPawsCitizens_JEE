package co.edu.unbosque.repositories;

import co.edu.unbosque.pojos.Image;

import java.util.List;
import java.io.IOException;

public interface ImageRepository {

    void storeRelation(String contextFilePath, String userName, String uploadDate, String description, String imagePath)
            throws IOException;

    List<Image> listImages(String contextFilePath, String userName) throws IOException;
}
