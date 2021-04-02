package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Entity representing core object of Movie Recommender System - Movie.
 *
 * @author Marek Petrovič
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "movie")
    private Set<Image> images= new HashSet<>();

    //TODO GENRE, ACTORS, DIRECTOR, RATING
//
//    @ManyToMany(mappedBy = "movies")
//    private Set<Genre> genres = new HashSet<>();
//
//    @ManyToMany(mappedBy = "movies")
//    private Set<Actor> actors = new HashSet<>();
//
//    @ManyToOne
//    private Director director;
//
//    @OneToMany(mappedBy = "movie")
//    private Set<Rating> ratings= new HashSet<>();

    /***
     * Creates Movie instance, sets only id. Others need to be set with setters
     * @param id
     */
    public Movie(Long id) {
        this.id = id;
    }

    /***
     * Creates Movie instance, without params. Need to be set with setters
     */
    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//TODO get/add Actor, Genre, Director

//    public Set<Genre> getGenres() {
//        return genres;
//    }
//
//    public Set<Actor> getActors() {
//        return actors;
//    }
//
//    public Set<Director> getDirectors() {
//        return directors;
//    }
//
//    public Set<Rating> getRatings() {
//        return ratings;
//    }
//
//    public void addActor(Actor actor){
//        this.actors.add(actor);
//        actor.addMovie(this);
//    }
//
//    public void addGenre(Genre genre){
//        this.genres.add(genre);
//        genre.addMovie(this);
//    }
//
//    public void setDirector(Director director) {
//        this.director = director;
//        director.addMovie(this);
//    }

    /***
     *
     * @param image
     */
    public void addImage(Image image){
        this.images.add(image);
        image.setMovie(this);
    }

    //TODO equals, hash code, CHECK!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getName(), movie.getName()) && Objects.equals(getDescription(), movie.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription());
    }
}
