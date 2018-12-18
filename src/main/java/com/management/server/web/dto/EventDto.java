package com.management.server.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Łukasz on 03.12.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class EventDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Date date;
    private String location;
//    private List<UserDto> users;

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }
}
