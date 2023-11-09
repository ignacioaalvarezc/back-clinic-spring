package com.voll.api.repository;

// IMPORTS.
import com.voll.api.domain.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

/**
 * THIS JPA REPOSITORY INTERFACE FOR MANAGING APPOINTMENT ENTITIES IN THE DATABASE.
 * Extends JpaRepository providing CRUD operations and query methods for the Appointment entity.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * CHECKS IF AN APPOINTMENT EXISTS FOR A SPECIFIC PATIENT WITHIN A GIVEN TIME RANGE.
     *
     * @param idPatient The ID of the patient.
     * @param firstHour The start time of the time range.
     * @param lastHour The end time of the time range.
     * @return True uf an appointment exists for the patient within the specified time range, false otherwise.
     */
    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);

    /**
     * CHECKS IF AN APPOINTMENT EXISTS FOR A SPECIFIC DOCTOR AT A GIVEN DATE AND TIME.
     *
     * @param idDoctor The ID of the doctor.
     * @param date The specific date and time of the appointment.
     * @return True if an appointment exists for the doctor at the given date and time, false otherwise.
     */
    Boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);
}
