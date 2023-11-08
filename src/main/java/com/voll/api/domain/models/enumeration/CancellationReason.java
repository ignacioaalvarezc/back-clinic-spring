package com.voll.api.domain.models.enumeration;

/**
 * THIS ENUMERATION CLASS REPRESENTING THE POSSIBLE REASONS FOR CANCELLATION OF AN APPOINTMENT.
 * This enumeration lists common cancellation reasons in the context of the application's domain.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public enum CancellationReason {

    // INDICATES THAT THE CANCELLATION WAS INITIATED BY THE PATIENT.
    PATIENT_GAVE_UP,

    // INDICATES THAT THE CANCELLATION WAS INITIATED BY THE DOCTOR.
    DOCTOR_CANCELED,

    // INDICATES OTHERS REASONS FOR CANCELLATIONS.
    OTHERS;
}
