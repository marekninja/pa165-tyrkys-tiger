package cz.muni.fi.pa165.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
//        return true;
        if (this == o) return true;
        if (!(o instanceof UserRating)) return false;
        UserRating that = (UserRating) o;
        return Objects.equals(getMovie(), that.getMovie()) && Objects.equals(getUser(), that.getUser())
                && Objects.equals(getStoryScore(), that.getStoryScore()) && Objects.equals(getVisualScore(),
                that.getVisualScore()) && Objects.equals(getActorScore(),
                that.getActorScore()) && Objects.equals(getOverallScore(), that.getOverallScore());
    }

    @Override
    public int hashCode() {
//        return 1;
        int hash = Objects.hash(getMovie(), getUser(), getStoryScore(), getVisualScore(), getActorScore(), getOverallScore());
        System.err.println("Vkladanie/contains hash: "+hash);
        return hash;
    }

    @Override
    public String toString() {
        return "User Rating{" +
                "id=" + id +
                ", movie title='" + movie.getName() + '\'' +
                ", user nickname='" + user.getNickName() + '\'' +
                ", storyScore='" + storyScore + '\'' +
                ", visualScore='" + visualScore + '\'' +
                ", actorScore=" + actorScore + '\'' +
                ", overallScore=" + overallScore +
                '}';
    }
}
