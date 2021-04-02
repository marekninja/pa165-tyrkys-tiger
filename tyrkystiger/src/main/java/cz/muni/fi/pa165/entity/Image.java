package cz.muni.fi.pa165.entity;

import javax.persistence.*;

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

    @Lob
    private byte[] image;

    private String imageMimeType;

    @ManyToOne
    private Movie movie;

    public Image(Long id) {
        this.id = id;
    }

    public Image() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        movie.addImage(this);
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

    //TODO equals, hashcode

}
