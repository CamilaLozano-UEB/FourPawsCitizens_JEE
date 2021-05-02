package co.edu.unbosque.repositories;

import co.edu.unbosque.pojos.Image;

import java.util.List;
import java.io.IOException;

public interface ImageRepository {

    /**
     * Interface for write the flat file
     *
     * @param contextFilePath, text path
     * @param userName,        user who saved the image
     * @param uploadDate,      image upload date
     * @param description,     image description
     * @param imagePath,       image path
     * @throws IOException
     */

    void storeRelation(String contextFilePath, String userName, String uploadDate, String description, String imagePath)
            throws IOException;

    /**
     * Interface for read the flat file
     *
     * @param contextFilePath, text path
     * @param userName,        user who saved the image
     * @return
     * @throws IOException
     */

    List<Image> listImages(String contextFilePath, String userName) throws IOException;
}
