package cz.muni.fi.pa165.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Basic view of User object. Ratings are not included.
 *
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String nickName;

    private String passwordHash;

    private String name;

    private String email;

    //JACKSON DELETES *IS* FROM BOOLEAN ATTRIBUTES - SO IN JSON THIS IS: administrator
    private boolean isAdministrator;

    private LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getNickName(), userDTO.getNickName()) && Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getEmail());
    }
}
