package com.management.server.web.dto.mapper;

import com.management.server.core.model.Event;
import com.management.server.web.dto.EventDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 03.12.2018
 */
@Component
@AllArgsConstructor
public class EventMapper {
    private final ModelMapper modelMapper;
//    private final UserMapper userMapper;

    public EventDto mapToDto(Event entity) {
        EventDto dto = modelMapper.map(entity, EventDto.class);
//        dto.setUsers(entity.getUsers().stream()
//                .map(userMapper::mapToDto)
//                .collect(Collectors.toList()));
        return dto;
    }

    public Event mapToEntity(EventDto dto) {
        Event entity = modelMapper.map(dto, Event.class);
//        entity.setUsers(dto.getUsers().stream()
//                .map(userMapper::mapToEntity)
//                .collect(Collectors.toSet()));
        return entity;
    }

    public Set<EventDto> mapToDtoSet(Set<Event> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }
}
