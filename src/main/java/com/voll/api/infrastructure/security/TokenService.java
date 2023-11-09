package com.voll.api.infrastructure.security;

// IMPORTS.
import com.voll.api.domain.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * SERVICE CLASS RESPONSIBLE FOR GENERATING AND VALIDATING JSON WEB TOKENS (JWT) FOR USER AUTHENTICATION.
 * This class provides methods to generate JWT tokens based on user details and verify the authenticity of tokens.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * GENERATES A JWT TOKEN BASED ON USER DETAILS.
     *
     * @param user The user object containing necessary details for token generation.
     * @return Generated JWT token as a string.
     * @throws RuntimeException If an error occurs during token generation.
     */
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll chi")
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating JWT token");
        }
    }

    /**
     * VERIFIES AND RETRIEVES THE SUBJECT (USERNAME) FROM A JWT TOKEN.
     *
     * @param token The JWT token as string.
     * @return The subject (username) extracted from the token.
     * @throws RuntimeException If the token is invalid or subject cannot be extracted.
     */
    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("voll chi")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token");
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Invalid verifier");
        }
        return verifier.getSubject();
    }

    /**
     * GENERATES THE EXPIRATION DATE FOR THE JWT TOKEN /CURRENT TIME +2 HOURS).
     *
     * @return Instant representing the expiration date.
     */
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
