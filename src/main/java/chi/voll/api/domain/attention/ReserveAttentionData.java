package chi.voll.api.domain.attention;

import chi.voll.api.domain.medico.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReserveAttentionData(Long id, @NotNull Long idPatient, Long idDoctor, @NotNull @Future LocalDateTime date, Speciality speciality) {
}
