package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 *
 * @author Peter Mravec
 */
@Getter
@Setter
public class PersonCreateDTO {

    @NotBlank(message = "Name cannot be null or whitespace.")
    @Size(max = 50)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonCreateDTO)) return false;
        PersonCreateDTO personCreateDTO = (PersonCreateDTO) o;
        return Objects.equals(getName(), personCreateDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
