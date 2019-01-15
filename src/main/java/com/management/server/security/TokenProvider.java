package com.management.server.security;

import com.management.server.core.exception.AuthException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.management.server.core.exception.ExceptionMessage.BAD_TOKEN;
import static com.management.server.core.exception.ExceptionMessage.EXPIRED_TOKEN;

/**
 * Created by Łukasz on 13.01.2019
 */
@Slf4j
@Component
public class TokenProvider {

    public static final String SECRET = "secretCode";
    public static final int EXPIRATION_TIME = 43200000;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        Claims claims = Jwts.claims()
                .setSubject(userDetails.getUsername());
        claims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        }
        catch (MalformedJwtException | SignatureException e) {
            throw new AuthException(BAD_TOKEN.toString());
        } catch (ExpiredJwtException e) {
            throw new AuthException(EXPIRED_TOKEN.toString());
        }
    }

    public String getUsernamFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}