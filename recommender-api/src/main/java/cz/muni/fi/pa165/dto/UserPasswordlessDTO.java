package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPasswordlessDTO {

    private Long id;

    @NotBlank(message = "Nickname cannot be null or whitespace.")
    private String nickName;

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
        if (!(o instanceof UserPasswordlessDTO)) return false;
        UserPasswordlessDTO userDTO = (UserPasswordlessDTO) o;
        return Objects.equals(getNickName(), userDTO.getNickName()) && Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getEmail());
    }
}
