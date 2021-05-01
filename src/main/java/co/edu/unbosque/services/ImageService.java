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
