package com.voll.api.repository;

// IMPORTS.
import com.voll.api.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**}
 * JPA REPOSITORY INTERFACE FOR MANAGING USER ENTITIES IN THE DATABASE.
 * Extends JpaRepository providing CRUD operations and a custom query method for the user entity.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * RETRIEVES USER DETAILS BASED ON THE USER'S LOGIN (USERNAME).
	 *
	 * @param username The login (username) of the user.
	 * @return UserDetails object containing user information.
	 */
	UserDetails findByLogin(String username);
}
