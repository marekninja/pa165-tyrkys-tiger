package cz.muni.fi.pa165.jpql;

import cz.muni.fi.pa165.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * To store aggregated scores of Movie
 * @author Marek Petrovič
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDummy {
    private Movie movie;
    private Double storyScore;
    private Double visualScore;
    private Double actorScore;
    private Double overallScore;

}
