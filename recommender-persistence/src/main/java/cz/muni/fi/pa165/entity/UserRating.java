package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRating)) return false;
        UserRating that = (UserRating) o;
        return Objects.equals(getMovie(), that.getMovie()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getStoryScore(), that.getStoryScore()) && Objects.equals(getVisualScore(), that.getVisualScore()) && Objects.equals(getActorScore(), that.getActorScore()) && Objects.equals(getOverallScore(), that.getOverallScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getUser(), getStoryScore(), getVisualScore(), getActorScore(), getOverallScore());
    }
}
