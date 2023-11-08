package com.voll.api.domain.validations.appointments;

// IMPORTS.
import com.voll.api.domain.dto.appointment.CancelAppointmentData;

/**
 * THIS INTERFACE DEFINES THE CONTRACT FOR VALIDATING ATTENTION RESERVATION DATA.
 * Classes implementing this interface must provide a validation method.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public interface CancellationValidator {
    /**
     * VALIDATES THE PROVIDED ATTENTION RESERVATION DATA.
     *
     * @param data The attention reservation data to be validated.
     */
    public void validate(CancelAppointmentData data);
}
