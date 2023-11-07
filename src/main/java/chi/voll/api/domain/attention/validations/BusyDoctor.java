package chi.voll.api.domain.attention.validations;

// IMPORTS.
import chi.voll.api.repository.AttentionRepository;
import chi.voll.api.domain.attention.ReserveAttentionData;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE SPECIFIED DOCTOR IS ALREADY BUSY AT THE SPECIFIED TIME FOR A NEW ATTENTION RESERVATION.
 * It implements attention validator interface.
 * Implements the validation logic for doctor's availability.
 * Validates if the doctor is busy at the specified time.
 */
@Component
public class BusyDoctor implements AttentionValidator {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private AttentionRepository attentionRepository;

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE THE SPECIFIED DOCTOR IS NOT ALREADY BUSY AT THE SPÉCIFIED TIME
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException If the specified doctor is busy at the specified time.
     */
    public void validate(ReserveAttentionData data) {
        if(data.idDoctor()==null)
            return; // No doctor specified, validation is not required.
        var busyDoctor = attentionRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
        if(busyDoctor) {
            throw new ValidationException("This doctor is already busy at this time.");
        }
    }
}
