package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
public class UserRatingDTO {
    private Integer storyScore;

    private Integer visualScore;

    private Integer actorScore;

    private Integer overallScore;

    private MovieDetailDTO movieDetailDTO;
}
