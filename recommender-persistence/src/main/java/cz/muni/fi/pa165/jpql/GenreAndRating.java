package cz.muni.fi.pa165.jpql;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.UserRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Marek Petrovič
 */
@Getter
@Setter
@AllArgsConstructor
public class GenreAndRating implements Comparable<GenreAndRating>{
    Genre genre;
    Float overallScore;

    @Override
    public int compareTo(GenreAndRating o) {
        return getOverallScore().compareTo(o.getOverallScore());
    }
}
