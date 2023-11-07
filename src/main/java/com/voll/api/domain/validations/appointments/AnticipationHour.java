package com.voll.api.domain.validations.appointments;

// IMPORTS.
import com.voll.api.domain.dto.appointment.ReserveAppointmentData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * THIS COMPONENT CLASS VALIDATE IF AN ATTENTION RESERVATION IS MADE WITH AT LEAST 30 MINUTES OF ANTICIPATION.
 * It implements attention validator interface.
 * Implements the validation logic for reservation data.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class AnticipationHour implements AttentionValidator {

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE IT'S MADE WITH AT LEAST 30 MINUTES OF ANTICIPATION.
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException If the reservation is made with less than 30 minutes of anticipation.
     *
     */
    public void validate(ReserveAppointmentData data) {
        var currentTime = LocalDateTime.now();
        var attentionTime = data.date();
        var Before30Min = Duration.between(currentTime,attentionTime).toMinutes()<30;
        if(Before30Min){
            throw new ValidationException("Consultations must be scheduled 30 minutes in advance.");
        }

    }
}
