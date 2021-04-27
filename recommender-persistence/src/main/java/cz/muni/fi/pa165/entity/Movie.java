package cz.muni.fi.pa165.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @OneToOne(mappedBy = "movieTitle", orphanRemoval = true)
    private Image imageTitle;

    @OneToMany(mappedBy = "movieGallery", orphanRemoval = true)
    private Set<Image> gallery = new HashSet<>();

    @PastOrPresent
    private LocalDate yearMade;

    //    maybe make enum for countries
    private String countryCode;

    private Integer lengthMin;

    @ManyToMany
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    private Set<Person> actors = new HashSet<>();

    @ManyToOne
    private Person director;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private Set<UserRating> ratings = new HashSet<>();

    public void addToGallery(Image image){
        image.setMovieGallery(this);
        this.gallery.add(image);
    }

    public void removeFromGallery(Image image){

        this.gallery.remove(image);
    }

    public void addUserRating(UserRating userRating){
        userRating.setMovie(this);
        this.ratings.add(userRating);

    }

    public void removeUserRating(UserRating userRating){
        this.ratings.remove(userRating);
    }

    public void addActor(Person actor){
//        actor.setActor(true);
        this.actors.add(actor);
    }

    public void removeActor(Person actor){
        this.actors.remove(actor);
//        actor.getActorsMovies().remove(this);
    }

    public void addGenre(Genre genre){
        this.genres.add(genre);
    }

    public void removeGenre(Genre genre){
        this.genres.remove(genre);
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
