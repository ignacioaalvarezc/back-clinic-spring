package com.voll.api.infrastructure.security;

// IMPORTS.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * CONFIGURATION CLASS FOR SECURITY SETTINGS IN THE APPLICATION.
 * This class defines various security-related configurations, such as URL permissions,
 * authentication manager, password encoder, and custom security filters.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	// DEPENDENCY INJECTIONS.
	@Autowired
	public SecurityFilter securityFilter;

	/**
	 * CONFIGURES THE SECURITY FILTER CHAIN FOR THE APPLICATION.
	 * Defines URL permissions, session management, authentication requirements, and custom security filters.
	 *
	 * @param httpSecurity HttpSecurity object for configuring security settings.
	 * @return SecurityFilterChain with configured security settings.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.requestMatchers(HttpMethod.POST, "/login").permitAll()
				.requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	/**
	 * CONFIGURES THE AUTHENTICATION MANAGER FOR THE APPLICATION.
	 *
	 * @param authenticationConfiguration AuthenticationConfiguration object for obtaining the authentication manager.
	 * @return Instance for authentication purposes.
	 * @throws Exception If an error occurs during configuration.
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	/**
	 * CONFIGURES THE PASSWORD ENCODER FOR SECURELY ENCODING AND VALIDATING PASSWORDS.
	 *
	 * @return PasswordEncoder implementation (BCryptPasswordEncoder) for password security.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

