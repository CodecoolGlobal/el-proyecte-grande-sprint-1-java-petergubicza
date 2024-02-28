package com.codecool.trivia.security.jwt;

import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.logger.Logger;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
  private static final Logger logger = new ConsoleLogger();

  @Value("${codecool.app.jwtSecret}")
  private String jwtSecret;

  @Value("${codecool.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {
    UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

    return Jwts.builder().setSubject(userPrincipal
                    .getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
  }

  public String getUserNameFromJwtToken (String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(key())
              .build()
              .parse(authToken);
      return true;

    } catch (MalformedJwtException e) {
      logger.logError(String.format("Invalid JWT token: %s", e.getMessage()));
    } catch (ExpiredJwtException e) {
      logger.logError(String.format("JWT token is expired: %s", e.getMessage()));
    } catch (UnsupportedJwtException e) {
      logger.logError(String.format("JWT token is unsupported: %s", e.getMessage()));
    } catch (IllegalArgumentException e) {
      logger.logError(String.format("JWT claims string is empty: %s", e.getMessage()));
    }
    return false;
  }
}
