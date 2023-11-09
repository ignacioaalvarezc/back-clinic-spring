package com.voll.api.domain.services;

// IMPORTS.
import com.voll.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * THIS SERVICE CLASS IMPLEMENTS USER DETAIL SERVICE INTERFACE FOR USER AUTHENTICATION.
 * Is responsible for loading user details from the database based on the provided
 * username during authentication.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Service
public class AuthenticationService implements UserDetailsService {

	// DEPENDENCY INJECTIONS.
	@Autowired
	private UserRepository userRepository;

	/**
	 * LOADS USER DETAILS FROM THE DATABASE BASED ON THE PROVIDED USERNAME.
	 *
	 * @param username The username of the user to load.
	 * @return Object containing user details if found.
	 * @throws UsernameNotFoundException Exception if the user with the given username is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByLogin(username);
	}
}
