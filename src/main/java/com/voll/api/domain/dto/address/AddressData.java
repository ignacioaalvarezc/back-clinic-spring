package com.voll.api.domain.dto.address;

import jakarta.validation.constraints.NotBlank;

public record AddressData(
		@NotBlank(message = "El nombre de la street es obligatorio")
		String street,
		@NotBlank
		String district,
		@NotBlank
		String city,
		@NotBlank
		String number,
		@NotBlank
		String complement) {
}
