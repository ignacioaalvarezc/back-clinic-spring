package com.voll.api.domain.dto.appointment;

import com.voll.api.domain.models.enumeration.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReserveAttentionData(Long id, @NotNull Long idPatient, Long idDoctor, @NotNull @Future LocalDateTime date, Speciality speciality) {
}
