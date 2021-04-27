package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO to create rating for Movie
 * MovieId to reference Movie
 * UserId to reference User
 *
 */
@Getter
@Setter
public class UserRatingCreateDTO {
    private Long id;

    private Long userId;

    private Long movieId;

    private Integer storyScore;

    private Integer visualScore;

    private Integer actorScore;

    private Integer overallScore;

    private MovieDetailDTO movieDetailDTO;
}
