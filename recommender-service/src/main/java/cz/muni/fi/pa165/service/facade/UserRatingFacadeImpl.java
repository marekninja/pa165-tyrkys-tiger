package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserRatingCreateDTO;
import cz.muni.fi.pa165.facade.UserRatingFacade;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matej Turek
 */
@Service
@Transactional
public class UserRatingFacadeImpl implements UserRatingFacade {
    @Override
    public void createUserRating(UserRatingCreateDTO userRatingCreateDTO) {

    }

    @Override
    public UserRatingCreateDTO findUserRatingById(Long id) {
        return null;
    }

    @Override
    public List<UserRatingCreateDTO> findUserRatingsByUser(UserDTO user) {
        return null;
    }

    @Override
    public List<UserRatingCreateDTO> findUserRatingsByMovie(MovieDetailDTO movie) {
        return null;
    }

    @Override
    public UserRatingCreateDTO updateUserRating(UserRatingCreateDTO userRatingCreateDTO) {
        return null;
    }

    @Override
    public void deleteUserRating(UserRatingCreateDTO userRatingCreateDTO) {

    }
}
