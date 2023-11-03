package chi.voll.api.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import chi.voll.api.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico()==null) {
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());

        if(!medicoActivo) {
            throw new ValidationException("Este medico ya tiene una cita para este horario.");
        }
    }
}
