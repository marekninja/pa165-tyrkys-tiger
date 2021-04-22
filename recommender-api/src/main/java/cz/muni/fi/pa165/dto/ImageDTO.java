package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO to upload Image to Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
public class ImageDTO {
    private byte[] image;
    private String imageMimeType;

    private Long movieId;
}
