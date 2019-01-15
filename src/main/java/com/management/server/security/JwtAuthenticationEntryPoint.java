package com.management.server.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Łukasz on 13.01.2019
 */
public class JwtAuthenticationEntryPoint implements Serializable, AuthenticationEntryPoint {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}