package cz.muni.fi.pa165.dto;

import lombok.*;

import java.util.Objects;

/**
 * Object used for User authentications.
 *
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticateDTO {

    private Long userId;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof UserAuthenticateDTO)) return false;
        UserAuthenticateDTO that = (UserAuthenticateDTO) o;
        return Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getPassword());
    }
}
