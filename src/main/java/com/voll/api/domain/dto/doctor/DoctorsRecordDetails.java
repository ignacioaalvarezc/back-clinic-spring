package com.voll.api.domain.dto.doctor;

import com.voll.api.domain.dto.address.AddressData;
import com.voll.api.domain.models.enumeration.Speciality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorsRecordDetails(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d[4,6]", message = "Formato de documento invalido")
        String documento,
        @NotBlank
        Speciality speciality,
        @NotNull
        @Valid
        AddressData direccion) {
}