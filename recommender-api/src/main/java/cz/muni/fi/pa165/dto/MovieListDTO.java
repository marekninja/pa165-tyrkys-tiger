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

    private Long Id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private ImageDetailDTO imageTitle;

    private Float overallScoreAgg;

    private Set<GenreDTO> genres = new HashSet<>();
}
