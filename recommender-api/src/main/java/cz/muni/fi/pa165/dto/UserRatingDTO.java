package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class UserRatingDTO {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer storyScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer visualScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer actorScore;

    @NotNull
    @Min(0)
    @Max(10)
    private Integer overallScore;
}
