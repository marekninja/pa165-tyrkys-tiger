package cz.muni.fi.pa165.dto;

import lombok.*;

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

    private String nickName;

    private String name;

    private String email;

    private boolean isAdministrator;

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
