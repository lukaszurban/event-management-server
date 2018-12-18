package com.management.server.web.dto;

import com.management.server.core.model.RoleType;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Łukasz on 03.12.2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto implements Serializable {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleType> roles;
    private Set<EventDto> events;

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", events=" + events +
                '}';
    }
}
