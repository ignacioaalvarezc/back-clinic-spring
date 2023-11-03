package chi.voll.api.domain.consulta.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos) {
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();
        var diferenciaDe30Min = Duration.between(ahora,horaDeConsulta).toMinutes()<30;
        if(diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con 30 minutos de anticipación.");
        }

    }
}
