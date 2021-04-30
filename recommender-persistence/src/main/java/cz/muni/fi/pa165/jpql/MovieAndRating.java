package cz.muni.fi.pa165.jpql;

import cz.muni.fi.pa165.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Marek Petrovič
 */
@Getter
@AllArgsConstructor
public class MovieAndRating {
    private Movie movie;
    private Double overallScore;

}
