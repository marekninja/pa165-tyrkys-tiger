package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO to view Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
public class MovieDetailDTO {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String description;

    private ImageDetailDTO imageTitle;

    private Set<ImageDetailDTO> gallery = new HashSet<>();

    @PastOrPresent
    private LocalDate yearMade;

    @Length(min = 0, max = 10)
    private String countryCode;

    @Min(0)
    @Max(500)
    private Integer lengthMin;

    private Set<GenreDTO> genres = new HashSet<>();

    private Set<PersonDTO> actors = new HashSet<>();

    private PersonDTO director;

    private Double ratingAgg;

    private UserRatingViewDTO ratingUser;

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
