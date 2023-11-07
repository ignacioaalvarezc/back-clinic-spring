package com.voll.api.domain.dto.appointment;

// IMPORTS.
import com.voll.api.domain.models.Appointment;

import java.time.LocalDateTime;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING DETAILED APPOINTMENT INFORMATION.
 * This DTO class follows Java records syntax, providing a concise representation of data.
 * It includes a specialized constructor to transform an Appointment entity into this DTO format.
 * It encapsulates essential appointment details such as:
 * @param id Appointment ID.
 * @param idPatient ID of the patient attending the appointment.
 * @param idDoctor ID of the doctor attending the appointment.
 * @param date Appointment date.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record AppointmentDetailData(Long id, Long idPatient, Long idDoctor, LocalDateTime date) {

    /**
     * CONSTRUCTS AN APPOINTMENT DETAIL DATA OBJECT BASED ON THE PROVIDES APPOINTMENT ENTITY.
     *
     * @param appointment The appointment entity from which data is extracted.
     */
    public AppointmentDetailData(Appointment appointment) {
            this(appointment.getId(), appointment.getPatient().getId(), appointment.getDoctor().getId(), appointment.getFecha());
        }

}
