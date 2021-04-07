package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;


/**
 * Entity representing core object of Movie Recommender System - Movie.
 *
 * @author Marek Petrovič
 */
@Entity
@Table(name = "movies")
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

    @OneToOne(mappedBy = "movieTitle")
    private Image imageTitle;

    @OneToMany(mappedBy = "movieGallery")
    private Set<Image> gallery = new HashSet<>();

//    @Temporal(TemporalType.DATE)
    private LocalDate yearMade;

    @Enumerated
    private Locale.IsoCountryCode countryCode;

    private Integer lengthMin;
    //TODO yearMade, country, length - getters, setters

    //TODO GENRE, ACTORS, DIRECTOR, RATING

//    @ManyToMany
//    @Enumerated(EnumType.STRING)
//    private Set<Genre> genres = new HashSet<>();
//
//    @ManyToMany(mappedBy = "moviesActed")
//    private Set<Person> actors = new HashSet<>();
//
//    @ManyToOne
//    private Person director;
//
//    @OneToMany(mappedBy = "movie")
//    private Set<Rating> ratings= new HashSet<>();

    /***
     * Creates Movie instance, sets only id. Others need to be set with setters
     * @param id Long unique identifier in DB
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

    public Set<Image> getGallery() {
        return gallery;
    }

    public Image getImageTitle() {
        return imageTitle;
    }

    public LocalDate getYearMade() {
        return yearMade;
    }

    public Locale.IsoCountryCode getCountryCode() {
        return countryCode;
    }

    public Integer getLengthMin() {
        return lengthMin;
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

    public void setImageTitle(Image imageTitle) {
        imageTitle.setMovieTitle(this);
        this.imageTitle = imageTitle;
    }

    public void addToGallery(Image image){
        this.gallery.add(image);
        image.setMovieGallery(this);
    }

    public void setYearMade(LocalDate yearMade) {
        this.yearMade = yearMade;
    }

    public void setCountryCode(Locale.IsoCountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public void setLengthMin(Integer lengthMin) {
        this.lengthMin = lengthMin;
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
