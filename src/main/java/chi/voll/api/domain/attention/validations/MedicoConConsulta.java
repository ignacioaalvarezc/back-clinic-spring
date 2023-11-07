package chi.voll.api.domain.attention.validations;

import chi.voll.api.repository.ConsultaRepository;
import chi.voll.api.domain.attention.ReserveAttentionData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements AttentionValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validate(ReserveAttentionData data) {
        if(data.idDoctor()==null)
            return;
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(data.idDoctor(), data.date());
        if(medicoConConsulta) {
            throw new ValidationException("Este medico ya tiene una consulta en este horario.");
        }
    }
}
