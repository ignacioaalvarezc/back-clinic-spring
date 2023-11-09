package com.voll.api.infrastructure.security;

/**
 * DATA CLASS REPRESENTING A JSON WEB TOKEN (JWT) RECEIVED OR SENT BY THE APPLICATION.
 * This record encapsulates the JWT token as string.
 *
 * @param jwtToken The JSON web token as a string.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record JWTTokenData(String jwtToken) {
}
