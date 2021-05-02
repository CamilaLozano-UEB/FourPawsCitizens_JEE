package co.edu.unbosque.services;

import co.edu.unbosque.pojos.Image;
import co.edu.unbosque.repositories.ImageRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;


@Singleton
public class ImageService implements ImageRepository {
    private final String FILE_PATH = "/imageRelation.txt";

    /**
     * Method that writes the flat file with the parameter data and saves them.
     *
     * @param contextFilePath, text path
     * @param userName,        user who saved the image
     * @param uploadDate,      image upload date
     * @param description,     image description
     * @param imagePath,       image path
     * @throws IOException
     */
    @Override
    public void storeRelation(String contextFilePath, String userName, String uploadDate, String description, String imagePath)
            throws IOException {
        // TODO Auto-generated method stub
        File dir = new File(contextFilePath);
        File f = new File(contextFilePath + FILE_PATH);
        System.out.println(f.getAbsolutePath());

        if (!dir.exists())
            dir.mkdirs();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), StandardCharsets.UTF_8));
        bw.write(userName + "~" + uploadDate + "~" + description + "~" + imagePath + "\n");
        bw.close();

    }

    /**
     * * Method that reads the imageRelation.txt txt. Verify that they are from the same user,
     * take the images to return an image object and save them in a list of images of the user
     * to which they correspond
     *
     * @param contextFilePath, text path
     * @param userName,        user who saved the image
     * @return
     * @throws IOException
     */
    @Override
    public List<Image> listImages(String contextFilePath, String userName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(contextFilePath + FILE_PATH));
        String linea = br.readLine();
        List<Image> images = new ArrayList<>();

        while (linea != null) {
            if (linea.split("~")[0].equals(userName)) {
                String[] data = linea.split("~");
                images.add(new Image(data[1], data[2], data[3]));
            }
            linea = br.readLine();
        }
        br.close();

        return images;
    }
}
