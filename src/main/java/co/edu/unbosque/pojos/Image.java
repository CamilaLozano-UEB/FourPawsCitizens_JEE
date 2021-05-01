package co.edu.unbosque.pojos;

public class Image {
    private String uploadDate;
    private String description;
    private String path;

    public Image(String uploadDate, String description, String path) {
        this.uploadDate = uploadDate;
        this.description = description;
        this.path = path;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
