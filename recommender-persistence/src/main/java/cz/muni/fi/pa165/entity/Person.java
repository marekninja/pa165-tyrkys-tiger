package cz.muni.fi.pa165.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public void addDirectedMovies(Movie movie) {
        this.directedMovies.add(movie);
        this.isDirector = true;
        movie.setDirector(this);
    }

    public void addActorsMovies(Movie movie) {
        this.actorsMovies.add(movie);
        this.isActor = true;
        movie.addActor(this);
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
