package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
