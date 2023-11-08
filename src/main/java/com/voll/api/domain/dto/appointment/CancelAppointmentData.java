package com.voll.api.domain.dto.appointment;

// IMPORTS.
import com.voll.api.domain.models.enumeration.CancellationReason;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 *  THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING THE DATA REQUIRED TO CANCEL AN APPOINTMENT.
 *  This class uses the record feature to create an immutable class with automatically generated getters and constructors.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record CancelAppointmentData(
        @NotNull
        Long id,
        CancellationReason reason) {

    /**
     * CONSTRUCTS A CANCEL APPOINTMENT DATA OBJECT WITH THE PROVIDED APPOINTMENT ID AND CANCELLATION REASON.
     *
     * @param id The unique identifier of the appointment to be canceled.
     * @param reason The reason for cancelig the appointment.
     */
    public CancelAppointmentData {
        Objects.requireNonNull(id, "ID cannot be null");
    }


}
