package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * DTO to upload Image to Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
public class ImageDTO {

    @NotNull
    private byte[] image;
    @NotNull
    private String imageMimeType;
    @NotNull
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageDTO)) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return Arrays.equals(getImage(), imageDTO.getImage()) && Objects.equals(getImageMimeType(), imageDTO.getImageMimeType()) && Objects.equals(getMovieId(), imageDTO.getMovieId());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getImageMimeType(), getMovieId());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
