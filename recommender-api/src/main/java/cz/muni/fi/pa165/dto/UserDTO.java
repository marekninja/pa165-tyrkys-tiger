package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

    @NotNull
    private Long id;

    @NotBlank(message = "Nickname cannot be null or whitespace.")
    private String nickName;

    @NotBlank(message = "Password cannot be null or whitespace.")
    private String password;

    private String name;

    @NotBlank(message = "Email cannot be null or whitespace.")
    @Email(message = "Please provide a valid email address")
    private String email;

    private boolean isAdministrator;

    @Past(message = "Date of birth must be in a past.")
    private LocalDate dateOfBirth;

    // ratings not needed

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
