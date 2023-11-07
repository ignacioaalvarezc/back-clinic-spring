package com.voll.api.repository;

import com.voll.api.domain.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AttentionRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByPatientIdAndDateBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByDoctorIdAndDate(Long idMedico, LocalDateTime fecha);
}
