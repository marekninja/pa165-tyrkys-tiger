package cz.muni.fi.pa165.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/***
 * Entity for images of Movie. Has ManyToOne relationship with Movie
 *
 * @author Marek Petrovič
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(nullable = false)
    private byte[] image;

    @NotBlank
    @Column(nullable = false)
    private String imageMimeType;

    @OneToOne
    private Movie movieTitle;

    @ManyToOne
    private Movie movieGallery;

    @NotNull
    private Boolean isTitleImage;

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
