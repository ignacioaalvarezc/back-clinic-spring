package chi.voll.api.validations;

import chi.voll.api.domain.consulta.ConsultaRepository;
import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;

public class MedicoConConsulta {

    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico()==null)
            return;
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndData(datos.idMedico(),datos.fecha());
        if(medicoConConsulta) {
            throw new ValidationException("Este medico ya tiene una consulta en este horario.");
        }
    }
}
