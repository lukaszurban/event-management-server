package com.management.server.web.dto.mapper;

import com.management.server.core.model.User;
import com.management.server.web.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 03.12.2018
 */
@Component
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;
    private final EventMapper eventMapper;

    public UserDto mapToDto(User entity) {
        UserDto dto = modelMapper.map(entity, UserDto.class);
        dto.setRoles(entity.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet()));
        dto.setEvents(eventMapper.mapToDtoSet(entity.getEvents()));
        return dto;
    }

    public User mapToEntity(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public List<UserDto> mapToDtoList(List<User> entities) {
        return entities.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
