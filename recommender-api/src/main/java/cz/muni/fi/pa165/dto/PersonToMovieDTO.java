package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 *
 *
 */
@Getter
@Setter
public class PersonToMovieDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long movieId;

}
