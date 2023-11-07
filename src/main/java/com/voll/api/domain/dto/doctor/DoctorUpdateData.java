package com.voll.api.domain.dto.doctor;

import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(@NotNull Long id, String nombre, String documento, AddressData direccion) {

}
