package com.voll.api.domain.dto.patient;

import com.voll.api.domain.models.Patient;

public record PatientListData(Long id, String nombre, String email, String documento) {

    public PatientListData(Patient patient) {
        this(patient.getId(), patient.getNombre(), patient.getEmail(), patient.getDocumento());
    }
}
