package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserRatingDTO;
import cz.muni.fi.pa165.dto.UserRatingViewDTO;

import java.util.List;

/**
 * @author Matej Turek
 */
public interface UserRatingFacade {

    UserRatingViewDTO createUserRating(UserRatingDTO userRatingDTO);

    UserRatingViewDTO findUserRatingById(Long id);

    List<UserRatingViewDTO> findUserRatingsByUser(UserDTO user);

    List<UserRatingViewDTO> findUserRatingsByMovie(MovieDetailDTO movie);

    UserRatingViewDTO updateUserRating(UserRatingDTO userRatingDTO);

    void deleteUserRating(Long id);
}
