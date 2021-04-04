package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/***
 * Entity for images of Movie. Has ManyToOne relationship with Movie
 *
 * @author Marek Petrovič
 */
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(nullable = false)
    private byte[] image;

    @NotNull
    @Column(nullable = false)
    private String imageMimeType;

    @OneToOne
    private Movie movieTitle;

    @ManyToOne
    private Movie movieGallery;

    public Image(Long id) {
        this.id = id;
    }

    public Image() {
    }

    public Movie getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(Movie movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Movie getMovieGallery() {
        return movieGallery;
    }

    public void setMovieGallery(Movie movieGallery) {
        this.movieGallery = movieGallery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    //TODO equals, hashcode check
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image1 = (Image) o;
        return Arrays.equals(getImage(), image1.getImage()) && getImageMimeType().equals(image1.getImageMimeType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getImageMimeType());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
