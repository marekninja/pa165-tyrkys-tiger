package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ImageDao;
import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Marek Petrovič
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    private final ImageDao imageDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, ImageDao imageDao) {

        this.movieDao = movieDao;
        this.imageDao = imageDao;
    }

    @Override
    public Movie findById(Long id) {
        Validator.validate(this.getClass(),id,"id was null");
        return movieDao.findById(id);
    }

    //TODO test
    @Override
    public MovieAndRating findByIdWithRating(Long id) {
        Validator.validate(this.getClass(),id, "id was null");

        return movieDao.findByIdWithRating(id);
    }

    @Override
    public List<MovieAndRating> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode) {
        if (yearMade != null && yearMade.getYear() > LocalDate.now().getYear()){
            throw new IllegalArgumentException("It is not yet possible to search movies from future! " +
                    "yearMade was "+yearMade.getYear());
        }
        return movieDao.findByParameters(genreList,personList,movieName,yearMade,countryCode);
    }


    @Override
    public List<MovieAndRating> getRecommendedMovies(List<Genre> genres, User user) {
        Validator.validate(this.getClass(),genres,new Object() {}.getClass().getEnclosingMethod().getName()+
                " parameter: genres was null");
        Validator.validate(this.getClass(),user,new Object() {}.getClass().getEnclosingMethod().getName()+
                " parameter: user was null");
        int maxOfGenre = 2;
        return movieDao.getMoviesOfGenres(genres,maxOfGenre,user);
    }

    @Override
    public Movie create(Movie movie) {
        Validator.validate(this.getClass(),movie,"movie was null");
        movieDao.create(movie);
        return movie;
    }

    @Override
    public void delete(Movie movie) {
        Validator.validate(this.getClass(),movie,"movie was null");
        movieDao.remove(movie);
    }


    @Override
    public void setImageTitle(Movie movie, Image imageTitle) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),imageTitle,"imageTitle to set was null");
        movie.setImageTitle(imageTitle);

        Image oldImage = movie.getImageTitle();
        movieDao.update(movie);
        imageDao.remove(oldImage);
    }

    @Override
    public void addToGallery(Movie movie, Image image) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),image,"image to change was null");
        movie.addToGallery(image);
        movieDao.update(movie);
    }

    @Override
    public void removeFromGallery(Movie movie, Image image) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),image,"image was null");
        movie.removeFromGallery(image);
        movieDao.update(movie);
        imageDao.remove(image);
    }

    @Override
    public void addActor(Movie movie, Person person) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),person,"actor was null");
        movie.addActor(person);
        movieDao.update(movie);
    }

    @Override
    public void removeActor(Movie movie, Person person) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),person,"person was null");
        movie.removeActor(person);
        movieDao.update(movie);
    }

    @Override
    public void changeDirector(Movie movie, Person person) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),person,"actor was null");
        movie.setDirector(person);
        movieDao.update(movie);
    }

    @Override
    public void addGenre(Movie movie, Genre genre) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),genre,"actor was null");
        movie.addGenre(genre);
        movieDao.update(movie);
    }

    @Override
    public void removeGenre(Movie movie, Genre genre) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Validator.validate(this.getClass(),genre,"actor was null");
        movie.removeGenre(genre);
        movieDao.update(movie);
    }

    @Override
    public void updateMovieAttrs(Movie movie) {
        Validator.validate(this.getClass(),movie,"movie was null");
        Movie original = movieDao.findById(movie.getId());
        original.setName(movie.getName());
        original.setDescription(movie.getDescription());
        original.setYearMade(movie.getYearMade());
        original.setCountryCode(movie.getCountryCode());
        original.setLengthMin(movie.getLengthMin());
        movieDao.update(original);
    }
}
