package com.voll.api.domain.models;

// IMPORTS.
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * REPRESENTS A USER IN THE APPLICATION.
 * This implements the UserDetails interface required by Spring Security,
 * to provide user authentication and authorization details.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String clave;

	/**
	 * RETURNS THE AUTHORITIES (ROLES) ASSIGNED TO THE USER.
	 * In this case, the user is assigned the "ROLE_USER" role.
	 *
	 * @return A list of GrantedAuthority representing user's roles.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * RETURNS THE PASSWORD ASSOCIATED WITH THE USER.
	 *
	 * @return The user's password.
	 */
	@Override
	public String getPassword() {
		return clave;
	}

	/**
	 * RETURNS THE USERNAME (LOGIN) ASSOCIATED WITH THE USER.
	 *
	 * @return The user's username.
	 */
	@Override
	public String getUsername() {
		return login;
	}

	/**
	 * INDICATES IF THE USER ACCOUNT HAS NOT EXPIRED.
	 *
	 * @return Always returns true, indicating the account is non-expired.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * INDICATES IF THE USER ACCOUNT IS NOT LOCKED.
	 *
	 * @return Always returns true, indicating the account is non-locked.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * INDICATES IF THE USER CREDENTIALS (PASSWORD) ARE NON-EXPIRED.
	 *
	 * @return Always returns true, indicating the credentials are non-expired.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * INDICATES IF THE USER IS ENABLED OR DISABLED.
	 *
	 * @return Always returns true, indicating the user is enabled.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
