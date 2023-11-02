package chi.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id, @NotNull Long idPaciente, Long idMedico, @NotNull @Future LocalDateTime fecha) {
}
