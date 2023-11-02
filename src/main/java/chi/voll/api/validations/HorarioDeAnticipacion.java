package chi.voll.api.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.xml.bind.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

public class HorarioDeAnticipacion {
    public void validar(DatosAgendarConsulta datos) throws ValidationException {
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();
        var diferenciaDe30Min = Duration.between(ahora,horaDeConsulta).toMinutes()<30;
        if(diferenciaDe30Min){
            throw new ValidationException("Las consultas deben programarse con 30 minutos de anticipación.");
        }

    }
}
