package cz.muni.fi.pa165.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a User in Movie recommender application.
 *
 * @author Matej Turek
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nickname cannot be null or whitespace.")
    @Column(nullable = false, unique = true)
    private String nickName;

    @NotBlank(message = "Password cannot be null or whitespace.")
    @Column(nullable = false)
    private String passwordHash;

    private String name;

    @NotBlank(message = "Email cannot be null or whitespace.")
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false, unique = true)
    private String email;

    private boolean isAdministrator;

    @Past(message = "Date of birth must be in a past.")
    private LocalDate dateOfBirth;

    @BatchSize(size = 100)
    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<UserRating> ratings = new HashSet<>();

    public User(long id) {
        this.id = id;
    }

    public void addRating(UserRating rating) {
        rating.setUser(this);
        this.ratings.add(rating);
    }

    public void removeRating(UserRating rating){
        this.ratings.remove(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getNickName(), user.getNickName()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isAdministrator=" + isAdministrator +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
