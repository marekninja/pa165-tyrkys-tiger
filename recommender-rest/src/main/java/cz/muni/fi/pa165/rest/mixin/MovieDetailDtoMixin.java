package cz.muni.fi.pa165.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.muni.fi.pa165.dto.ImageDetailDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Marek Petrovič
 */
@JsonIgnoreProperties({"imageTitle","gallery"})
public abstract class MovieDetailDtoMixin {
    private ImageDetailDTO imageTitle;
    private Set<ImageDetailDTO> gallery = new HashSet<>();

}
