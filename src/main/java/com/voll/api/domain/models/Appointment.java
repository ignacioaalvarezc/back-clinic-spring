package com.voll.api.domain.models;

import com.voll.api.domain.models.enumeration.CancellationReason;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Patient patient;

    private LocalDateTime fecha;

    @Column(name = "motivo_cancelamiento")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime fecha) {
        this.doctor = doctor;
        this.patient = patient;
        this.fecha=fecha;
    }

    public void cancelar(CancellationReason motivo) {
        this.cancellationReason =motivo;
    }
}
