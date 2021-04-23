package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

/**
 * DTO for Movie creation
 * Contains all the relations
 * @author Marek Petrovič
 */
@Getter
@Setter
public class MovieCreateDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String description;

    private ImageDetailDTO imageTitle;

    private Set<ImageDetailDTO> gallery;

    @PastOrPresent
    private LocalDate yearMade;

    @Min(0)
    @Max(10)
    private String countryCode;

    @Min(0)
    @Max(500)
    private Integer lengthMin;

    private List<GenreDTO> genres;

    private List<PersonDTO> actors;

    private PersonDTO director;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCreateDTO)) return false;
        MovieCreateDTO that = (MovieCreateDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }
}
