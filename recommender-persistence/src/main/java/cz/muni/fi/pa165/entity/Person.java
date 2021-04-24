package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * FOR MILESTONE 1 EVALUATION
 * Entity representing a Person in Movie recommender application.
 *
 * @author Peter Mravec
 */
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "director")
    private Set<Movie> directedMovies = new HashSet<>();

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> actorsMovies = new HashSet<>();

    private boolean isDirector;

    private boolean isActor;

    public Person() {}

    public Person(long id) {
        this.id = id;
    }

    public Person(@NotNull String name, boolean isDirector, boolean isActor) {
        this.name = name;
        this.isDirector = isDirector;
        this.isActor = isActor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getDirectedMovies() {
        return directedMovies;
    }

    public void addDirectedMovies(Movie movie) {
        this.directedMovies.add(movie);
        this.isDirector = true;
        movie.setDirector(this);
    }

    public Set<Movie> getActorsMovies() {
        return actorsMovies;
    }

    public void addActorsMovies(Movie movie) {
        this.actorsMovies.add(movie);
        this.isActor = true;
        movie.addActor(this);
    }

    public boolean isDirector() {
        return isDirector;
    }

    public void setDirector(boolean director) {
        isDirector = director;
    }

    public boolean isActor() {
        return isActor;
    }

    public void setActor(boolean actor) {
        isActor = actor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Person)) return false;
        Person other = (Person) o;
        return Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
