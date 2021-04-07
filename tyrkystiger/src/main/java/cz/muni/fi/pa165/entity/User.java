package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a User in Movie recommender application.
 *
 * @author Matej Turek
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String nickName;

    @NotNull
    @Column(nullable = false)
    private String passwordHash;

    private String name;

    @NotNull
    @Email(message = "Please provide a valid email address")
    @Column(nullable = false)
    private String email;

    private boolean isAdministrator;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<UserRating> ratings = new HashSet<>();

    public User() {}

    public User(long id) {
        this.id = id;
    }

    public User(@NotNull String nickName, @NotNull String passwordHash, String name, @NotNull @Email String email, boolean isAdministrator, LocalDate dateOfBirth) {
        this.nickName = nickName;
        this.passwordHash = passwordHash;
        this.name = name;
        this.email = email;
        this.isAdministrator = isAdministrator;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<UserRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<UserRating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(UserRating rating) {
        this.ratings.add(rating);
        rating.setUser(this);
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
