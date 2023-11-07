package com.voll.api.domain.dto.patient;

import com.voll.api.domain.models.Address;
import com.voll.api.domain.models.Patient;

public record PatientsRecordDetails(Long id, String nombre, String email, String documento, String telefono, Address address) {

    public PatientsRecordDetails(Patient patient) {
        this(patient.getId(), patient.getNombre(), patient.getEmail(), patient.getDocumento(), patient.getTelefono(), patient.getAddress());
    }
}
