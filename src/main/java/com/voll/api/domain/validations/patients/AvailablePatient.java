package com.voll.api.domain.validations.patients;

// IMPORTS.
import com.voll.api.domain.dto.appointment.ReserveAttentionData;
import com.voll.api.domain.validations.appointments.AttentionValidator;
import com.voll.api.repository.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE SPECIFIED PATIENT IS ACTIVE AND AVAILABLE FOR ATTENTION RESERVATION.
 * It implements attention validator interface.
 * Implements the validation logic for patient's available.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class AvailablePatient implements AttentionValidator {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private PatientRepository patientRepository;

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE THE SPECIFIED PATIENT IS ACTIVE AND AVAILABLE.
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException Exception if the specified patient is inactive or not available for the reservation.
     */
    public void validate(ReserveAttentionData data) {
        if(data.idPatient()==null) {
            return; // No patient specified, validation is not required.
        }
        var availablePatient = patientRepository.findStatusById(data.idPatient());

        if(!availablePatient) {
            throw new ValidationException("Appointments cannot be scheduled with inactive patients in the system.");
        }
    }
}
