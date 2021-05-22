package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

/**
 * @author Matej Turek
 */
public interface UserRatingFacade {

    UserRatingDTO createUserRating(UserRatingCreateDTO userRatingCreateDTO);

    UserRatingDTO findUserRatingById(Long id);

    List<UserRatingDTO> findUserRatingsByUser(UserDTO user);

    List<UserRatingDTO> findUserRatingsByMovie(MovieDetailDTO movie);

    UserRatingDTO updateUserRating(UserRatingDTO userRatingDTO);

    void deleteUserRating(Long id);
}
