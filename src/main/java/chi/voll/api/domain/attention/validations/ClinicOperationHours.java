package chi.voll.api.domain.attention.validations;

// IMPORTS.
import chi.voll.api.domain.attention.ReserveAttentionData;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

/**
 * THIS COMPONENT CLASS VALIDATES IF THE ATTENTION RESERVATION FALLS WITHIN THE CLINIC'S OPERATING HOURS.
 * It implements attention validator interface.
 * Implements the validation logic for reservation dana.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Component
public class ClinicOperationHours implements AttentionValidator {

    /**
     * VALIDATES THE ATTENTION RESERVATION DATA TO ENSURE IT FALLS WITHIN THE CLINIC'S OPERATING HOURS.
     *
     * @param data The attention reservation data to be validated.
     * @throws ValidationException if the reservation falls outside the clinic's operating hours.
     */
    public void validate(ReserveAttentionData data) {
        var Sunday = DayOfWeek.SUNDAY.equals(data.date().getDayOfWeek());
        var beforeOpening = data.date().getHour()<9;
        var afterClosing = data.date().getHour()>19;
        if (Sunday || beforeOpening || afterClosing) {
            throw new ValidationException("The clinic's operating hours are Monday to Saturday from 9:00 a.m. to 7:00 p.m.");
        }
    }
}
