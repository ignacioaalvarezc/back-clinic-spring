package chi.voll.api.validations;

import chi.voll.api.domain.consulta.ConsultaRepository;
import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico()==null)
            return;
        var medicoConConsulta = consultaRepository.existsByMedicoIdAndDatos(datos.idMedico(),datos.fecha());
        if(medicoConConsulta) {
            throw new ValidationException("Este medico ya tiene una consulta en este horario.");
        }
    }
}
