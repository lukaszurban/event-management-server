package com.management.server.web.controller;

import com.management.server.web.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Łukasz on 03.12.2018
 */
public class AbstractController {

    public UserDto getLoggedUser() {
        return (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public URI getLocation(Long entityId) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(entityId)
                .toUri();
        return location;
    }
}
