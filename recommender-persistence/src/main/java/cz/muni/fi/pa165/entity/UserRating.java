package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Entity representing a UserRating in Movie recommender application.
 *
 * @author Peter Mravec
 */
@Entity
@Table(name = "userRatings")
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Movie movie;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    @Max(10)
    private Integer storyScore;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    @Max(10)
    private Integer visualScore;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    @Max(10)
    private Integer actorScore;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    @Max(10)
    private Integer overallScore;

    public UserRating() {}

    public UserRating(long id) {
        this.id = id;
    }

    public UserRating(@NotNull Movie movie, @NotNull User user) {
        this.movie = movie;
        this.user = user;
    }

    public UserRating(@NotNull Movie movie, @NotNull User user, @NotNull @Min(0) @Max(10) Integer storyScore, @NotNull @Min(0) @Max(10) Integer visualScore, @NotNull @Min(0) @Max(10) Integer actorScore, @NotNull @Min(0) @Max(10) Integer overallScore) {
        this.movie = movie;
        this.user = user;
        this.storyScore = storyScore;
        this.visualScore = visualScore;
        this.actorScore = actorScore;
        this.overallScore = overallScore;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStoryScore() {
        return storyScore;
    }

    public void setStoryScore(Integer storyScore) {
        this.storyScore = storyScore;
    }

    public Integer getVisualScore() {
        return visualScore;
    }

    public void setVisualScore(Integer visualScore) {
        this.visualScore = visualScore;
    }

    public Integer getActorScore() {
        return actorScore;
    }

    public void setActorScore(Integer actorScore) {
        this.actorScore = actorScore;
    }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserRating))
            return false;
        UserRating other = (UserRating) obj;
        if (user == null) {
            if (other.getUser() != null)
                return false;
        } else if (!user.equals(other.getUser()))
            return false;
        else if (movie == null) {
            if (other.getMovie() != null)
                return false;
        } else if (!movie.equals(other.getMovie()))
            return false;
        return true;
    }
}
