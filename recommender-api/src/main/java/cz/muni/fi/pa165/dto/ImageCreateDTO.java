package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * DTO to upload Image to (already existing) Movie
 * Contains movieId to know which Movie to assign to
 *
 * @author Marek Petrovič
 */
@Getter
@Setter
public class ImageCreateDTO {

    @NotNull
    private byte[] image;
    @NotNull
    private String imageMimeType;
    @NotNull
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageCreateDTO)) return false;
        ImageCreateDTO imageCreateDTO = (ImageCreateDTO) o;
        return Arrays.equals(getImage(), imageCreateDTO.getImage()) && Objects.equals(getImageMimeType(), imageCreateDTO.getImageMimeType()) && Objects.equals(getMovieId(), imageCreateDTO.getMovieId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getImageMimeType(), getMovieId());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
