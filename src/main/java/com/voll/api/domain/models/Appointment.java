package com.voll.api.domain.models;

// IMPORTS.

import com.voll.api.domain.models.enumeration.CancellationReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * THIS MODEL CLASS REPRESENTS AN APPOINTMENT BETWEEN A DOCTOR AND A PATIENT.
 * This models the appointments made in the application, storing information such as the doctor, patient,
 * appointment date and cancellation reason if applicable.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDateTime date;
    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellation_reason;

    /**
     * CONSTRUCTS AN APPOINTMENT OBJECT WITH THE SPECIFIED DOCTOR, PATIENT AND APPOINTMENT DATE.
     *
     * @param doctor The doctor involved in the appointment.
     * @param patient The patient involved in the appointment.
     * @param date The date and time of the appointment
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    /**
     * CANCELS THE APPOINTMENT AND SETS THE CANCELLATION REASON.
     *
     * @param reason The reason for the cancellation.
     */
    public void cancel(CancellationReason reason) {
        this.cancellation_reason = reason;
    }
}
