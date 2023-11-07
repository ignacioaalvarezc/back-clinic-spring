package com.voll.api.domain.dto.appointment;

import com.voll.api.domain.models.Appointment;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {

        public DatosDetalleConsulta(Appointment appointment) {
            this(appointment.getId(), appointment.getPatient().getId(), appointment.getDoctor().getId(), appointment.getFecha());
        }

}
