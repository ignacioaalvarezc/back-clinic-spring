package chi.voll.api.domain.attention.validations;

import chi.voll.api.repository.ConsultaRepository;
import chi.voll.api.domain.attention.ReserveAttentionData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements AttentionValidator {

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validate(ReserveAttentionData data) {
        var primerHorario = data.date().withHour(7);
        var ultimoHorario = data.date().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(data.idPaciente(),primerHorario,ultimoHorario);
        if(pacienteConConsulta) {
            throw new ValidationException("El paciente ya tiene una consulta para este horario.");
        }
    }
}
