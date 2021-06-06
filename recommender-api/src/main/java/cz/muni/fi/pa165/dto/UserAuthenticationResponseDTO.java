package cz.muni.fi.pa165.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticationResponseDTO {

    @NotNull
    private UserPasswordlessDTO user;

    private String token;

}
