package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO to create rating for Movie
 * MovieId to reference Movie
 * UserId to reference User
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingCreateDTO {

    private Long id;

    private Long userId;

    private Long movieId;

    private Integer storyScore;

    private Integer visualScore;

    private Integer actorScore;

    private Integer overallScore;
}
