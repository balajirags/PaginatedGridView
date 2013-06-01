package balaji.example.model;

/**
 * Created by balajig on 5/30/13.
 */
public class Movie {

    String imageUrl = "";

    public Movie(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
