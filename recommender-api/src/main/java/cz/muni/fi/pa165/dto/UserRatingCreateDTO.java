package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO representation of UserRating without field id.
 *
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRatingCreateDTO {

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