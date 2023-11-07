package chi.voll.api.repository;

import chi.voll.api.domain.attention.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AttentionRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPatientIdAndDateBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByDoctorIdAndDate(Long idMedico, LocalDateTime fecha);
}
