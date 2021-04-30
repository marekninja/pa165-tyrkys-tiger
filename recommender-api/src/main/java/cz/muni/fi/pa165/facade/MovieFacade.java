package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

/**
 * Facade to handle all requests concerning Movie entity
 * Also contains core functionalities of System - Movie Recommendation and Search
 *
 * @author Marek Petrovič
 */
public interface MovieFacade {

    /**
     * To find Movie by id. Will show detail of ONE Movie
     * @param movieId Long Id of Movie
     * @return DTO with all the attributes to show Movie detail
     */
    MovieDetailDTO findMovieById(Long movieId);

    /**
     * To search for multiple movies by Parameters.
     * To view details of one Movie use {@link #findMovieById(Long)}
     * @param parametersDTO DTO on which search Parameters are passed
     *                      - Genres, Persons, yearMade, countryCode
     * @return List of Movies which fulfill the criteria
     */
    List<MovieListDTO> findMovieByParameters(ParametersDTO parametersDTO);


    /**
     * To get recommended Movies for User.
     * @param userDTO UserDTO needed for User ID
     * @return MovieListDTO with recommended Movies for User
     */
    List<MovieListDTO> getRecommendedMovies(UserDTO userDTO);

    /**
     * To create movie with all the needed relations
     * @param movieCreateDTO MovieCreateDTO which holds all the data filled on frontend
     * @return Long Movie ID - can be used to view the Movie detail
     */
    Long createMovie(MovieCreateDTO movieCreateDTO);

    /**
     * To update non-relational attributes of Movie - name, yearMade, countryMade...
     * @param movieDetailDTO all the relations of Movie filled
     * @return Long Movie ID
     */
    Long updateMovieAttrs(MovieDetailDTO movieDetailDTO);

    /**
     * Delete Movie
     * @param movieId Long Movie ID
     */
    void deleteMovie(Long movieId);

    /**
     * To change title Image with other image.
     * The old title image will be deleted.
     * @param imageCreateDTO ImageDTO uploaded Image with all the relations (with movie) filled
     */
    void changeTitleImage(ImageCreateDTO imageCreateDTO);

    /**
     * Adds Image to Movie gallery
     *
     * @param imageCreateDTO uploaded Image to add to gallery
     */
    void addImage(ImageCreateDTO imageCreateDTO);

    /**
     * To delete Image from Gallery
     * @param imageId  Long ID of Image
     */
    void deleteImage(Long imageId);

    /**
     * Adds Actor to already created Movie
     *
     * @param personDTO actor, contains id of movie
     */
    void addActor(PersonToMovieDTO personDTO);

    /**
     * Deletes Actor of already created Movie.
     * Does not delete Actor in DB, it deletes just it's relation with this Movie
     * @param personDTO actor contains id of Movie
     */
    void deleteActor(PersonToMovieDTO personDTO);

    void addGenre(GenreToMovieDTO genreToMovieDTO);

    void removeGenre(GenreToMovieDTO genreToMovieDTO);

    /**
     * Changes Director of already created movie with other.
     * The previous director loses relationship with this Movie
     * @param personDTO director
     */
    void changeDirector(PersonToMovieDTO personDTO);

}
