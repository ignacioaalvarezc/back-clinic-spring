package chi.voll.api.validations;

import chi.voll.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class HorarioFuncionamientoClinica {
    public void validar(DatosAgendarConsulta datos) {
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha());
        var antesDeApertura = datos.fecha().getHour()<9;
        var despuesDeCierre = datos.fecha().getHour()>19;
        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException("El horario de atención de la clínica es de Lunes a Sabado de 09:00 a 19:00 horas.")
        }
    }
}
