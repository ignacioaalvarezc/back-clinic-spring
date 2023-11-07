package com.voll.api.domain.dto.patient;

import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PatientRecordData(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 0, max = 15)
        String telefono,
        @NotBlank
        String documento,
        @NotNull
        @Valid
        AddressData direccion) {
}
