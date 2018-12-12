package com.management.server.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Łukasz on 28.11.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity(name = "event")
public class Event implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_SEQ")
    @SequenceGenerator(name = "EVENT_ID_SEQ",
            sequenceName = "EVENT_ID_SEQ",
            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "date")
    @NotNull
    private Date date;

    @Column(name = "location")
    @NotNull
    private String location;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
    private Set<User> users;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (date != null ? !date.equals(event.date) : event.date != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null) return false;
        return users != null ? users.equals(event.users) : event.users == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode() * name.hashCode() * location.hashCode();
    }
}
