package com.voll.api.domain.dto.appointment;

// IMPORTS.
import com.voll.api.domain.models.enumeration.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING DATA FOR RESERVING A MEDICAL APPOINTMENT.
 * This DTO class follows Java record syntax for a concise representation od data.
 * It includes validation annotations from Jakarta Bean Validation to ensure the integrity of the transferred data.
 * It encapsulates necessary information such as:
 * @param idPatient ID of the patient who booked the appointment.
 * @param idDoctor ID of the doctor attending the appointment.
 * @param date Appointment date.
 * @param speciality Doctor's specialty.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record ReserveAppointmentData(
        @NotNull
        Long idPatient,
        @NotNull
        Long idDoctor,
        @NotNull @Future
        LocalDateTime date,
        Speciality speciality) {
}
