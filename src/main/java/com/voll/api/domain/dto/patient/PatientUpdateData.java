package com.voll.api.domain.dto.patient;

import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        AddressData direccion) {
}
