package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.facade.UserRatingFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.UserRatingService;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matej Turek
 */
@Service
@Transactional(readOnly = true)
public class UserRatingFacadeImpl implements UserRatingFacade {

    private final UserRatingService userRatingService;

    private final UserService userService;

    private final MovieService movieService;

    private final BeanMappingService beanMappingService;

    @Inject
    public UserRatingFacadeImpl(UserRatingService service, UserService userService, MovieService movieService, BeanMappingService beanMappingService) {
        this.userRatingService = service;
        this.userService = userService;
        this.movieService = movieService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    @Transactional
    public UserRatingDTO createUserRating(UserRatingCreateDTO userRatingCreateDTO) {
        Validator.validate(this.getClass(), userRatingCreateDTO, "User rating cannot be null!");

        UserRating rating = beanMappingService.mapTo(userRatingCreateDTO, UserRating.class);
        User storedUser = userService.findUserById(userRatingCreateDTO.getUserId());
        Movie storedMovie = movieService.findById(userRatingCreateDTO.getMovieId());

        Validator.validate(this.getClass(), storedUser, "User doesn't exist!");
        Validator.validate(this.getClass(), storedMovie, "Movie doesn't exist!");

        UserRating userRating = userRatingService.createUserRating(rating, storedUser, storedMovie);

        return beanMappingService.mapTo(userRating, UserRatingDTO.class);
    }

    @Override
    public UserRatingDTO findUserRatingById(Long id) {
        Validator.validate(this.getClass(), id, "Id cannot be null!");

        return beanMappingService.mapTo(userRatingService.findUserRatingById(id), UserRatingDTO.class);
    }

    @Override
    public List<UserRatingDTO> findUserRatingsByUser(UserDTO user) {
        Validator.validate(this.getClass(), user, "UserDTO cannot be null!");

        User storedUser = beanMappingService.mapTo(user, User.class);
        return beanMappingService.mapTo(userRatingService.findUserRatingsByUser(storedUser), UserRatingDTO.class);
    }

    @Override
    public List<UserRatingDTO> findUserRatingsByMovie(MovieDetailDTO movie) {
        Validator.validate(this.getClass(), movie, "Movie cannot be null!");

        Movie storedMovie = beanMappingService.mapTo(movie, Movie.class);
        return beanMappingService.mapTo(userRatingService.findUserRatingsByMovie(storedMovie), UserRatingDTO.class);
    }

    @Override
    @Transactional
    public UserRatingDTO updateUserRating(UserRatingDTO userRatingDTO) {
        Validator.validate(this.getClass(), userRatingDTO, "UserRatingCreateDTO cannot be null!");

        UserRating storedRating = beanMappingService.mapTo(userRatingDTO, UserRating.class);

        // delete old
        userRatingService.deleteUserRating(storedRating);

        // create new
        User storedUser = userService.findUserById(userRatingDTO.getUserId());
        Movie storedMovie = movieService.findById(userRatingDTO.getMovieId());
        storedRating = userRatingService.createUserRating(storedRating, storedUser, storedMovie);

        return beanMappingService.mapTo(storedRating, UserRatingDTO.class);
    }

    @Override
    @Transactional
    public void deleteUserRating(Long id) {
        Validator.validate(this.getClass(), id, "ID cannot be null!");

        UserRating storedRating = userRatingService.findUserRatingById(id);
        userRatingService.deleteUserRating(storedRating);
    }
}
