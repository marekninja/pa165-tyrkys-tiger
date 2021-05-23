package cz.muni.fi.pa165.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Parameters filled when searching for a Movies by parameters
 * @author Marek Petrovič
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParametersDTO {
    List<GenreDTO> genreDTOList;
    List<PersonDTO> personDTOList;
    String movieName;
    Integer yearMade;
    String countryCode;
}
