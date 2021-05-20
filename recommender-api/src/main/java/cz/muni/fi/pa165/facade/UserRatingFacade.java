package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserRatingCreateDTO;
import cz.muni.fi.pa165.dto.UserRatingDTO;

import java.util.List;

/**
 * @author Matej Turek
 */
public interface UserRatingFacade {

    UserRatingDTO createUserRating(UserRatingCreateDTO userRatingCreateDTO);

    UserRatingDTO findUserRatingById(Long id);

    List<UserRatingDTO> findUserRatingsByUser(UserDTO user);

    List<UserRatingDTO> findUserRatingsByMovie(MovieDetailDTO movie);

    UserRatingDTO updateUserRating(UserRatingCreateDTO userRatingCreateDTO);

    void deleteUserRating(Long id);
}
