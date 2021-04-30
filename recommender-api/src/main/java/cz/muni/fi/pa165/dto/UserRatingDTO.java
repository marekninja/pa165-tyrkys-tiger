package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Long id;

    private Integer storyScore;

    private Integer visualScore;

    private Integer actorScore;

    private Integer overallScore;
}
