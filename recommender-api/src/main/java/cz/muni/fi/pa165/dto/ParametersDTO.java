package cz.muni.fi.pa165.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Parameters filled when searching for a Movies by parameters
 * @author Marek Petrovič
 */

@Getter
@Setter
public class ParametersDTO {
    List<GenreDTO> genreDTOList;
    List<PersonDTO> personDTOList;
    String movieName;
    Integer yearMade;
    String countryCode;
}
