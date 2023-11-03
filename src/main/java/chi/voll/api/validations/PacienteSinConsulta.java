package chi.voll.api.validations;

import chi.voll.api.domain.consulta.ConsultaRepository;
import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndDatosBetween(datos.idPaciente(),primerHorario,ultimoHorario);
        if(pacienteConConsulta) {
            throw new ValidationException("El paciente ya tiene una consulta para este horario.");
        }
    }
}
