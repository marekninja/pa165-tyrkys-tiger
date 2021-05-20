package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Just to view, does not need references
 * Dummy object to view Agregate of UserRatings of Movie
 * Used when getting MovieDetail/MovieList to view average of all scores of Movie
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingDTO {

    @NotNull
    private Long id;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer storyScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer visualScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer actorScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer overallScore;
}
