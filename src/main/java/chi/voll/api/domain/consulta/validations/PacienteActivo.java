package chi.voll.api.domain.consulta.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import chi.voll.api.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idPaciente()==null) {
            return;
        }
        var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

        if(!pacienteActivo) {
            throw new ValidationException("No se pueden agendar citas con pacientes inactivos en el sistema");
        }
    }
}
