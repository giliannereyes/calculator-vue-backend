package com.giliannereyes.calculatorspring.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Service responsible for creating and validating JWT access tokens.
 */
@Service
public class JwtService {
  private final SecretKey signingKey;
  private final long expirationMs;

  /**
   * Creates a JWT service with configured signing key and expiration time.
   *
   * @param secret signing secret used for HMAC-SHA
   * @param expirationMs token validity duration in milliseconds
   */
  public JwtService(
      @Value("${jwt.secret}") String secret,
      @Value("${jwt.expiration-ms}") long expirationMs
  ) {
    this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.expirationMs = expirationMs;
  }

  /**
   * Generates a signed JWT token for the given username.
   *
   * @param username username to embed as subject
   * @return compact JWT string
   */
  public String generateToken(String username) {
    Date now = new Date();
    Date expiry = new Date(now.getTime() + expirationMs);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(signingKey)
        .compact();
  }

  /**
   * Extracts the token subject (username).
   *
   * @param token JWT string
   * @return username stored in token subject
   */
  public String extractUsername(String token) {
    return parseClaims(token).getSubject();
  }

  /**
   * Validates token signature and expiration.
   *
   * @param token JWT string
   * @return true when token is syntactically valid and not expired
   */
  public boolean isTokenValid(String token) {
    try {
      parseClaims(token);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(signingKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}
