package chi.voll.api.domain.attention.validations;

// IMPORTS.
import chi.voll.api.domain.attention.ReserveAttentionData;
import chi.voll.api.repository.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE SPECIFIED DOCTOR IS AVAILABLE FOR A NEW ATTENTION RESERVATION.
 * It implements attention validator interface.
 * Implements the validation logic for doctor's availability.
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
    public void validate(ReserveAttentionData data) {
        if(data.idDoctor()==null) {
            return;
        }
        var availableDoctor = doctorRepository.findStatusById(data.idDoctor());
        if(!availableDoctor) {
            throw new ValidationException("This doctor already has an appointment for this time.");
        }
    }
}
