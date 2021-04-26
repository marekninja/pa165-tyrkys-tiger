package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

/**
 * FOR MILESTONE 1 EVALUATION, Transactional for tests sake
 * Implementation of {@link MovieDao}
 *
 * @author Marek Petrovič
 */
@Repository
public class MovieDaoImpl implements MovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Movie movie) {
        if (movie == null){
            throw new IllegalArgumentException("Movie was null");
        }

        entityManager.persist(movie);
    }

    @Override
    public List<Movie> findAll() {

        return entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie findById(Long Id) {
        if (Id == null){
            throw new IllegalArgumentException("Id was null");
        }

        return entityManager.find(Movie.class, Id);
    }

    @Override
    public List<Movie> findByName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name was null");
        }

        return entityManager.createQuery("select m from Movie m where m.name = :name", Movie.class)
                .setParameter("name",name)
                .getResultList();
    }

    @Override
    public List<Movie> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode) {

        Map<String, Object> parameterMap = new HashMap<>();
        List<String> where = new ArrayList<>();
        where.add("1 = 1");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT m from Movie m ");


        if (genreList != null && genreList.size() > 0){
            StringBuilder genreBuilder = new StringBuilder();
            genreBuilder.append("( ");

            for (int i = 0; i < genreList.size(); i++) {
                Genre genre = genreList.get(i);
                genreBuilder.append(":" + "gen").append(i).append(" MEMBER OF m.genres");
                parameterMap.put("gen"+i, genre);

                if (i+1 < genreList.size()){
                    genreBuilder.append(" and ");
                }
            }
            genreBuilder.append(" )");
            where.add(genreBuilder.toString());
        }
        if (personList != null && personList.size() > 0){
            StringBuilder personBuilder = new StringBuilder();

            personBuilder.append("(( ");
            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                personBuilder.append(":" + "act").append(i).append(" MEMBER OF m.actors");
                parameterMap.put("act"+i, person);

                if (i+1 < personList.size()){
                    personBuilder.append(" and ");
                }
            }
            personBuilder.append(" )");
            personBuilder.append(" or m.director IN :personlist");
            parameterMap.put("personlist",personList);
            personBuilder.append(" )");

            where.add(personBuilder.toString());
        }
        if (movieName != null){
            where.add("m.name like :name");
            parameterMap.put("name","%"+movieName+"%");

        }
        if (yearMade != null && yearMade.getYear() <= LocalDate.now().getYear()){
            where.add("m.yearMade = :yearmade");
            parameterMap.put("yearmade",yearMade);

        }
        if (countryCode != null){
            where.add("m.countryCode = :countrycode");
            parameterMap.put("countrycode",countryCode);

        }
        stringBuilder.append("where ").append(StringUtils.join(where, " and "));
        Query query = entityManager.createQuery(stringBuilder.toString(), Movie.class);
        for (String key:parameterMap.keySet()) {
            query.setParameter(key,parameterMap.get(key));
        }

        return (List<Movie>) query.getResultList();
    }

    @Override
    public Movie update(Movie movie) {
        if (movie == null){
            throw new IllegalArgumentException("Movie was null");
        }

        return entityManager.merge(movie);
    }

    @Override
    public void remove(Movie movie) {
        if (movie == null){
            throw new IllegalArgumentException("Image was null");
        }
        entityManager.remove(this.findById(movie.getId()));
    }

    //todo test
    @Override
    public List<Movie> getMoviesOfGenres(List<Genre> genres, int maxOfGenre, User user) {
        Set<Movie> movies = new HashSet<>();
        for (Genre genre: genres) {
            List<Movie> found = entityManager.createQuery("SELECT m from Movie m " +
                    "join m.genres g join m.ratings r " +
                    "where g = :genre and m not in :movies and m not in ( select rat.movie from UserRating rat where rat.user = :user)" +
                    "group by m " +
                    "order by avg(r.overallScore)", Movie.class)
                    .setParameter("user",user)
                    .setParameter("genre",genre)
                    .setParameter("movies",movies)
                    .setMaxResults(maxOfGenre)
                    .getResultList();
            movies.addAll(found);
        }
        return new ArrayList<>(movies);
    }
}
