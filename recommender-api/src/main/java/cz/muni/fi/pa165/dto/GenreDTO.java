package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 *
 * @author Marek Petrovič
 */
@Getter
@Setter
public class GenreDTO {
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreDTO)) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return Objects.equals(getName(), genreDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
