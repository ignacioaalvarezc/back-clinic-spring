package chi.voll.api.domain.medico;

import chi.voll.api.domain.direccion.DatosDireccion;
import chi.voll.api.domain.medico.Especialidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d[4,6]", message = "Formato de documento invalido")
        String documento,
        @NotBlank
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}