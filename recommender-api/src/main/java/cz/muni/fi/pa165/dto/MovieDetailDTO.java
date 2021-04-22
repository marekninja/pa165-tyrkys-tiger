package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * DTO to view Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
public class MovieDetailDTO {


    private Long id;

    private String name;

    private String description;

    private ImageDTO imageTitle;

    private Set<ImageDTO> gallery = new HashSet<>();

    private LocalDate yearMade;

    private String countryCode;

    private Integer lengthMin;

    private Set<GenreDTO> genres = new HashSet<>();

    private Set<PersonDTO> actors = new HashSet<>();

    private PersonDTO director;

    private Set<UserRatingDTO> userRatingDTOSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDetailDTO)) return false;
        MovieDetailDTO that = (MovieDetailDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }
}
