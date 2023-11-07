package chi.voll.api.domain.attention.validations;

import chi.voll.api.domain.attention.ReserveAttentionData;
import chi.voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements AttentionValidator {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(ReserveAttentionData data) {
        if(data.idPaciente()==null) {
            return;
        }
        var pacienteActivo = patientRepository.findActivoById(data.idPaciente());

        if(!pacienteActivo) {
            throw new ValidationException("No se pueden agendar citas con pacientes inactivos en el sistema");
        }
    }
}
