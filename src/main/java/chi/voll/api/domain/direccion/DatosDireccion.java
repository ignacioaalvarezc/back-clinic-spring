package chi.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
		@NotBlank(message = "El nombre de la calle es obligatorio")
		String calle, 
		@NotBlank
		String distrito, 
		@NotBlank
		String ciudad, 
		@NotBlank
		String numero, 
		@NotBlank
		String complemento) {
}
