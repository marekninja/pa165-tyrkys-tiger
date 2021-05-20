package cz.muni.fi.pa165.jpql;

import cz.muni.fi.pa165.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Marek Petrovič
 */
@Getter
public class MovieAndRating {
    private Movie movie;
    private Double overallScore;

    public MovieAndRating(Movie movie, Double overallScore) {
        this.movie = movie;
        if (overallScore == null) {
            this.overallScore = Double.valueOf(0);
        } else {
            this.overallScore = overallScore;
        }
    }
}
