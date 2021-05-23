package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 *
 * @author Marek Petrovič
 */
@Getter
@Setter
public class GenreDTO {

    @NotNull
    private Long id;

    @NotBlank(message = "Name cannot be null or whitespace.")
    @Size(max = 50)
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
