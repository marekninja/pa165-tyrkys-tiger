package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO representation for creating user (no id needed)
 *
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @NotBlank(message = "Nickname cannot be null or whitespace.")
    private String nickName;

    @NotBlank(message = "Password cannot be null or whitespace.")
    private String password;

    private String name;

    @NotBlank(message = "Email cannot be null or whitespace.")
    @Email(message = "Please provide a valid email address")
    private String email;

    //JACKSON DELETES *IS* FROM BOOLEAN ATTRIBUTES - SO IN JSON THIS IS: administrator
    private boolean isAdministrator;

    @Past(message = "Date of birth must be in a past.")
    private LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof UserCreateDTO)) return false;
        UserCreateDTO userCreateDTO = (UserCreateDTO) o;
        return Objects.equals(getNickName(), userCreateDTO.getNickName()) && Objects.equals(getEmail(), userCreateDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getEmail());
    }
}
