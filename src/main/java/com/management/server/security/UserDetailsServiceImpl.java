package com.management.server.security;

import com.management.server.core.model.Role;
import com.management.server.core.model.User;
import com.management.server.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 13.01.2019
 */
@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found."));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorites(new ArrayList<>(user.getRoles()))
        );
    }

    private List<GrantedAuthority> getAuthorites(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
    }
}