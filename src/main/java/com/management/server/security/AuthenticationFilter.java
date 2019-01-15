package com.management.server.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Łukasz on 13.01.2019
 */
@AllArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER = "Authorization";

    private final UserDetailsServiceImpl userDetailsService;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader(HEADER);
            if (!StringUtils.isEmpty(token)) {
                tokenProvider.validateToken(token);
                String username = tokenProvider.getUsernamFromToken(token);
                UserDetails user = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
    }
}
