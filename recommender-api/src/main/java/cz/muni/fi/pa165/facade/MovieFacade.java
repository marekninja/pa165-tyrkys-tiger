package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.List;

/**
 * Facade to handle all requests concerning Movie
 *
 * @author Marek Petrovič
 */
public interface MovieFacade {

    /**
     * To find Movie by id. Will show detail of ONE Movie
     * @param movieId
     * @return
     */
    public MovieDetailDTO findMovieById(Long movieId);

    /**
     * To search for multiple movies by Parameters.
     * To view details of one Movie use {@link #findMovieById(Long)}
     * @param genreIds List of IDs of Genres to match
     * @param personIds List of IDs of Person to match - can be director/actor
     * @param movieName String name of Movie
     * @param yearMade Integer year in which Movie was made
     * @param countryCode String country code in which Movie was produced (USA, SVK, ...)
     * @return List of Movies, only with needed parameters for view
     */
    public List<MovieListDTO> findMovieByParameters(List<Long> genreIds, List<Long> personIds, String movieName, Integer yearMade, String countryCode);

    /**
     * To create movie with all the needed relations
     * @param movieCreateDTO MovieCreateDTO which holds all the data filled on frontend
     * @return Long Movie ID - can be used to view the Movie detail
     */
    public Long createMovie(MovieCreateDTO movieCreateDTO);

    /**
     * To update existing Movie - change any of the parameters/relations
     * @param movieCreateDTO all the relations of Movie filled
     * @return Long Movie ID
     */
    public Long updateMovie(MovieCreateDTO movieCreateDTO);

    /**
     * Delete Movie
     * @param movieId Long Movie ID
     */
    public void deleteMovie(Long movieId);

    /**
     * To change title Image with other image.
     * The old title image will be deleted.
     * @param imageDTO ImageDTO uploaded Image with all the relations (with movie) filled
     */
    public void changeTitleImage(ImageDTO imageDTO);

    /**
     * Adds Image to Movie gallery
     *
     * @param imageDTO uploaded Image to add to gallery
     */
    public void addImage(ImageDTO imageDTO);

    /**
     * To delete Image from Gallery
     * @param imageId  Long ID of Image
     */
    public void deleteImage(Long imageId);

    /**
     * Adds Actor to already created Movie
     *
     * @param movieId Long ID of Movie
     * @param personDTO actor
     */
    public void addActor(Long movieId, PersonDTO personDTO);

    /**
     * Deletes Actor of already created Movie.
     * Does not delete Actor in DB, it deletes just it's relation with this Movie
     * @param movieId Long ID of Movie
     * @param personDTO actor
     */
    public void deleteActor(Long movieId, PersonDTO personDTO);

    /**
     * Changes Director of already created movie with other.
     * The previous director loses relationship with this Movie
     * @param movieId Long ID of Movie
     * @param personDTO director
     */
    public void changeDirector(Long movieId, PersonDTO personDTO);

    /**
     * Removes userRating relation with Movie.
     * Should also remove userRating from DB, because it now has no purpose.
     * @param userRatingDTO UserRating
     */
    public void deleteUserRating(UserRatingDTO userRatingDTO);
}
