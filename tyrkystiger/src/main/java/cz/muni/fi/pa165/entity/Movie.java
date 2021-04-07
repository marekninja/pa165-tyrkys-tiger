package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.*;


/**
 * FOR MILESTONE 1 EVALUATION
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
    @Past
    private LocalDate yearMade;

    @Enumerated
    private Locale.IsoCountryCode countryCode;

    private Integer lengthMin;

    //TODO GENRE
//    @ManyToMany
//    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    private Set<Person> actors = new HashSet<>();

    @ManyToOne
    private Person director;

    @OneToMany(mappedBy = "movie")
    private Set<UserRating> userRatings= new HashSet<>();

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

    //TODO get/add Genre


    public Set<Person> getActors() {
        return actors;
    }

    public Person getDirectors() {
        return director;
    }

    public Set<UserRating> getRatings() {
        return userRatings;
    }

    public void addActor(Person actor){
        this.actors.add(actor);
    }

    public void setDirector(Person director) {
        this.director = director;
    }

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
