package chi.voll.api.controller;

// IMPORTS.
import chi.voll.api.domain.usuario.AuthenticatedUserData;
import chi.voll.api.domain.usuario.Usuario;
import chi.voll.api.system.security.DatosJWTToken;
import chi.voll.api.system.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CONTROLLER CLASS RESPONSIBLE FOR USER AUTHENTICATION.
 * This Controller provides an endpoint to authenticate users and generate an access token,
 * allowing access to other endpoints in the system.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Gets the token for the assigned user, which gives access to the rest of the endpoints.")
public class AuthenticationController {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    /**
     * ENDPOINT TO AUTHENTICATE A USER AND GENERATE AN ACCESS TOKEN.
     *
     * @param authenticatedUserData The user's login credentials.
     * @return ResponseEntity containing the generated access token.
     */
    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid AuthenticatedUserData authenticatedUserData) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authenticatedUserData.login(),
                authenticatedUserData.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Usuario) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
