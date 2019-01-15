package com.management.server.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Łukasz on 28.11.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "person")
public class User implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ",
            sequenceName = "USER_ID_SEQ",
            allocationSize = 1)
    private Long id;


    @Column(name = "login", unique = true)
    @NotNull
    @Size(min = 3, message = "Login must be at least 3 characters long")
    private String login;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_event",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "event_id")
            }
    )
    @JsonIgnore
    private Set<Event> events = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", events=" + events +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return enabled == user.enabled &&
                Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, login, firstName, lastName, email, password, enabled);
    }
}
