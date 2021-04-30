package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * Only for view of Image
 * Doesn't contain movieId
 *
 * @author Marek Petrovič
 */
@Getter
@Setter
public class ImageDetailDTO {

    @NotNull
    private Long id;

    @NotNull
    private byte[] image;
    @NotNull
    private String imageMimeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageDetailDTO)) return false;
        ImageDetailDTO that = (ImageDetailDTO) o;
        return Arrays.equals(getImage(), that.getImage()) && Objects.equals(getImageMimeType(), that.getImageMimeType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getImageMimeType());
        result = 31 * result + Arrays.hashCode(getImage());
        return result;
    }
}
