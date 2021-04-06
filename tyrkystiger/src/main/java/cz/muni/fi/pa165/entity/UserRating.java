package cz.muni.fi.pa165.entity;

import javax.validation.constraints.NotNull;

/**
 * Entity representing a UserRating in Movie recommender application.
 *
 * @author Peter Mravec
 */
@Entity
@Table(name = "userRatings")
public class UserRating {
    public static final Integer MIN_VALUE_FOR_RATING = new Integer(0);
    public static final Integer MAX_VALUE_FOR_RATING = new Integer(10);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TO DO !!!!!!!
    @NotNull
    @Column(nullable = false)
    @ManyToOne(mappedBy = "XXXXX")
    private Movie movie;

    // TO DO !!!!!!!
    @NotNull
    @Column(nullable = false)
    @ManyToOne(mappedBy = "XXXXX")
    private User user;

    @NotNull
    @Column(nullable = false)
    @Min(MIN_VALUE_FOR_RATING)
    @Max(MAX_VALUE_FOR_RATING)
    private Integer storyScore;

    @NotNull
    @Column(nullable = false)
    @Min(MIN_VALUE_FOR_RATING)
    @Max(MAX_VALUE_FOR_RATING)
    private Integer visualScore;

    @NotNull
    @Column(nullable = false)
    @Min(MIN_VALUE_FOR_RATING)
    @Max(MAX_VALUE_FOR_RATING)
    private Integer actorScore;

    @NotNull
    @Column(nullable = false)
    @Min(MIN_VALUE_FOR_RATING)
    @Max(MAX_VALUE_FOR_RATING)
    private Integer overralScore;

    public UserRating() {}

    public UserRating(long id) {
        this.id = id;
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

    public Integer getOverralScoreô() {
        return overralScoreô;
    }

    public void setOverralScoreô(Integer overralScoreô) {
        this.overralScoreô = overralScoreô;
    }

    // TO DO !!!!!!!
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((movie == null) ? 0 : movie.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    // TO DO !!!!!!!
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserRating))
            return false;
        UserRating other = (UserRating) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }
}
