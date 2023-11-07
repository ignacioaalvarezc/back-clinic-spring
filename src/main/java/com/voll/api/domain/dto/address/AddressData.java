package com.voll.api.domain.dto.address;

// IMPORTS.
import jakarta.validation.constraints.NotBlank;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING ADDRESS INFORMATION.
 * The class follows Java record syntax for a concise representation.
 * This DTO class adheres to validation constraints using Jakarta Bean Validation annotations.
 * It encapsulates the details of a physical address, including:
 * @param street Street name.
 * @param district District name.
 * @param city City name.
 * @param number Street number.
 * @param complement Address complement.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record AddressData(
        @NotBlank(message = "The street name is required.")
		String street,
		@NotBlank(message = "The district name is required.")
		String district,
		@NotBlank(message = "The city name is required.")
		String city,
		@NotBlank(message = "The number of the street is required.")
		String number,
		@NotBlank
		String complement) {
}
