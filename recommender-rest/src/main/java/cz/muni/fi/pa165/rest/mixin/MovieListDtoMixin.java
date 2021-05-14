package cz.muni.fi.pa165.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.muni.fi.pa165.dto.ImageDetailDTO;

/**
 * @author Marek Petrovič
 */
@JsonIgnoreProperties({ "imageTitle" })
public abstract class MovieListDtoMixin {
    private ImageDetailDTO imageTitle;
}
