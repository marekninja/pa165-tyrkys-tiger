package cz.muni.fi.pa165.jpql;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.UserRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * JPQL helper to aggregate Score for Movie
 * @author Marek Petrovič
 */
@Getter
@Setter
@AllArgsConstructor
public class GenreAndRating implements Comparable<GenreAndRating>{
    Genre genre;
    Float overallScore;

    public GenreAndRating(){
    }

    public GenreAndRating(Genre genre, float overallScore){
        this.genre = genre;
        this.overallScore = overallScore;
    }

    public GenreAndRating(Genre genre, Double overallScore){
        this.genre = genre;
        this.overallScore = overallScore.floatValue();
    }

    @Override
    public int compareTo(GenreAndRating o) {
        //all is greater than null
        if (o == null ){
            return 1;
        }
        //if this null, then that bigger
        if (this.getOverallScore() == null & o.getOverallScore() != null){
            return -1;
        }
        //if both null, than equal
        if (this.getOverallScore() == null & o.getOverallScore() == null){
            return 0;
        }
        //if that null, this bigger
        if (this.getOverallScore() != null & o.getOverallScore() == null){
            return 1;
        }

        return getOverallScore().compareTo(o.getOverallScore());
    }
}
