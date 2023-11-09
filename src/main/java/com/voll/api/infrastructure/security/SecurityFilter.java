package com.voll.api.infrastructure.security;

// IMPORTS.
import com.voll.api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * CUSTOM SECURITY FILTER RESPONSIBLE FOR AUTHENTICATING REQUESTS BASED ON JWT TOKENS.
 * This filter intercepts incoming requests, extracts the JWT token from the authorization header,
 * validates it, and sets up the authentication context for authorized users.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    /**
     * FILTERS INCOMING REQUESTS, EXTRACTS AND VALIDATES JWT TOKENS, AND SETS UP AUTHENTICATION CONTEXT.
     *
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     * @param filterChain The filter chain for handling the request and response.
     * @throws ServletException If a servlet-related exception occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                        throws ServletException, IOException {
        // GET THE JWT TOKEN FROM THE AUTHORIZATION HEADER.
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer", "");
            var userToken = tokenService.getSubject(token);
            if (userToken != null) {
                // IF THE TOKEN IS VALID, AUTHENTICATE THE USER AND SET UP THE AUTHENTICATION CONTEXT.
                var user = userRepository.findByLogin(userToken);
                var authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // CONTINUE THE FILTER CHAIN FOR FURTHER PROCESSING OF THE REQUEST.
        filterChain.doFilter(request, response);
    }
}
