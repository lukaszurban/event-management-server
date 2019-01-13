package com.management.server.web.controller;

import com.management.server.core.exception.EmailExistsException;
import com.management.server.core.model.User;
import com.management.server.core.service.UserService;
import com.management.server.web.dto.UserDto;
import com.management.server.web.dto.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by Łukasz on 04.12.2018
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity getUsers() {
        List<UserDto> users = userMapper.mapToDtoList(userService.getUsers());
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        UserDto user = userMapper.mapToDto(userService.getUserById(userId));
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserDto userDto) throws EmailExistsException {
        User user = userService.createUser(userMapper.mapToEntity(userDto));

        return ResponseEntity.created(getLocation(user.getId())).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, userMapper.mapToEntity(userDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}/disable")
    public ResponseEntity disableUser(@PathVariable Long id) {
        User user = userService.disableUser(id);

        return ResponseEntity.created(getLocation(user.getId())).build();
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
