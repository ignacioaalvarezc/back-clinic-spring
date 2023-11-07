package com.voll.api.domain.validations.doctors;

// IMPORTS.
import com.voll.api.domain.dto.appointment.ReserveAppointmentData;
import com.voll.api.domain.validations.appointments.AttentionValidator;
import com.voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE SPECIFIED DOCTOR IS AVAILABLE FOR A NEW ATTENTION RESERVATION.
 * It implements attention validator interface.
 * Implements the validation logic for doctor's availability.
 * Validates if the doctor is in active status.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class AvailableDoctor implements AttentionValidator {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE THE SPECIFIED DOCTOR IS AVAILABLE.
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException Exception if the specified doctor is not available for the reservation.
     */
    public void validate(ReserveAppointmentData data) {
        if(data.idDoctor()==null) {
            return; // No doctor specified, validation is not required.
        }
        var availableDoctor = doctorRepository.findStatusById(data.idDoctor());
        if(!availableDoctor) {
            throw new ValidationException("This doctor is not available.");
        }
    }
}
