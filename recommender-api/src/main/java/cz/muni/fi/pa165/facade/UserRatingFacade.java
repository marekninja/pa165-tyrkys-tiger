package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserRatingCreateDTO;

import java.util.List;

/**
 * @author Matej Turek
 */
public interface UserRatingFacade {

    void createUserRating(UserRatingCreateDTO userRatingCreateDTO);

    UserRatingCreateDTO findUserRatingById(Long id);

    List<UserRatingCreateDTO> findUserRatingsByUser(UserDTO user);

    List<UserRatingCreateDTO> findUserRatingsByMovie(MovieDetailDTO movie);

    UserRatingCreateDTO updateUserRating(UserRatingCreateDTO userRatingCreateDTO);

    void deleteUserRating(UserRatingCreateDTO userRatingCreateDTO);
}
