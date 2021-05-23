package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
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
public class UserAuthenticationDTO {

    @NotBlank
    private String nickName;

    @NotBlank
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof UserAuthenticationDTO)) return false;
        UserAuthenticationDTO that = (UserAuthenticationDTO) o;
        return Objects.equals(getNickName(), that.getNickName()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getPassword());
    }
}
