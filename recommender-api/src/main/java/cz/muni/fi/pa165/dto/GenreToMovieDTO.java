package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 *
 * @author Marek Petrovič
 */
@Getter
@Setter
public class GenreToMovieDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreToMovieDTO)) return false;
        GenreToMovieDTO that = (GenreToMovieDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getMovieId(), that.getMovieId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMovieId());
    }
}
