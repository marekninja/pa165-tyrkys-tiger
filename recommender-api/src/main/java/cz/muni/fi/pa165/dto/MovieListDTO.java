package cz.muni.fi.pa165.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO to vie list of Movies
 * Does not contain all atributes of Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
public class MovieListDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 0, max = 50)
    @Setter(AccessLevel.NONE)
    private String description;

    private ImageDTO titleImage;

    private UserRatingListDTO userRatingListDTO;

    private Set<GenreDTO> genres = new HashSet<>();

    public void setDescription(String description) {
        this.description = description.substring(0,50);
    }
}
