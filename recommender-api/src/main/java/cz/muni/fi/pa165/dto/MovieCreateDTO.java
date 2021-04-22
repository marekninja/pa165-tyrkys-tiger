package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(nullable = false)
    @Size(max = 50)
    private String name;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    private ImageDTO titleImage;

    private List<ImageDTO> gallery;

    @PastOrPresent
    private LocalDate yearMade;

    @Min(0)
    @Max(10)
    private String countryCode;

    @Min(0)
    @Max(500)
    private Integer lengthMin;

    private List<Long> genreIds;

    private List<Long> actorIds;

    private Long directorId;

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
