package chi.voll.api.domain.attention;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {

        public DatosDetalleConsulta(Consulta consulta) {
            this(consulta.getId(), consulta.getPatient().getId(), consulta.getDoctor().getId(), consulta.getFecha());
        }

}
