package com.management.server.core.service;

import com.management.server.core.exception.EmailExistsException;
import com.management.server.core.exception.UserNotFoundException;
import com.management.server.core.model.RoleType;
import com.management.server.core.model.User;
import com.management.server.core.repository.RoleRepository;
import com.management.server.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//import com.management.server.core.repository.RoleRepository;

/**
 * Created by Łukasz on 29.11.2018
 */
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Transactional
    public User createUser(final User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("Account with that email address already exists: " + user.getEmail());
        }

        final User u = User.builder()
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .roles(new HashSet<>(Arrays.asList(roleRepository.findByName(RoleType.ROLE_USER))))
                .enabled(true)
                .build();

        return userRepository.save(u);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User entity = userRepository.getOne(id);
        entity.setLogin(user.getLogin());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEnabled(user.isEnabled());
        entity.setEmail(user.getEmail());
        return entity;
    }

    public User disableUser(Long id) {
        User user = getUserById(id);
        user.setEnabled(false);
        return userRepository.save(user);
    }

    private boolean emailExist(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
