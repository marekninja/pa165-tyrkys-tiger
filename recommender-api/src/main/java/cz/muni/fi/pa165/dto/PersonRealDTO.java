package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 *
 * @author Peter Mravec
 */
@Getter
@Setter
public class PersonRealDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 60)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonRealDTO)) return false;
        PersonRealDTO personDTO = (PersonRealDTO) o;
        return Objects.equals(getName(), personDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
