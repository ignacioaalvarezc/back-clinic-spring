package com.voll.api.domain.dto.user;

import java.util.Objects;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING THE INFORMATION OF AN AUTHENTICATED USER.
 * This class utilizes the record concept to define an immutable class with read-only fields.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record AuthenticatedUserData(String login, String password) {

    /**
     * CONSTRUCTOR FOR AUTHENTICATION USER DATA CLASS.
     *
     * @param login The username of the authenticated user.
     * @param password The password of the authenticated user.
     */
    public AuthenticatedUserData {
        Objects.requireNonNull(login, "Username cannot be null.");
        Objects.requireNonNull(password, "Password cannot be null.");
    }
}
