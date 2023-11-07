package com.voll.api.domain.validations.appointments;

// IMPORTS.
import com.voll.api.repository.AttentionRepository;
import com.voll.api.domain.dto.appointment.ReserveAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE SPECIFIED PATIENT ALREADY HAS AN ATTENTION RESERVATION AT THE SPECIFIED TIME.
 * It implements attention validator interface.
 * Implements the validation logic for patient's appointment clashes.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class PatientWithoutAppointment implements AttentionValidator {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private AttentionRepository attentionRepository;

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE THE SPECIFIED PATIENT DOES NOT HAVE ANOTHER APPOINTMENT AT THE SPECIFIED TIME.
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException Exception if the specified patient has a consultation at the specified time.
     */
    public void validate(ReserveAppointmentData data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var patientWithAttention = attentionRepository.existsByPatientIdAndDateBetween(data.idPatient(),firstHour,lastHour);
        if(patientWithAttention) {
            throw new ValidationException("The patient already has a consultation for this time.");
        }
    }
}
