package chi.voll.api.validations;

import chi.voll.api.domain.consulta.ConsultaRepository;
import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;

public class PacienteSinConsulta {

    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos) {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndDatosBetween(datos.idPaciente(),primerHorario,ultimoHorario);
        if(pacienteConConsulta) {
            throw new ValidationException("");
        }
    }
}
