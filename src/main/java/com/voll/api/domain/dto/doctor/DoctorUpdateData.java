package com.voll.api.domain.dto.doctor;

// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING UPDATED DOCTOR INFORMATION RECEIVED FROM API REQUESTS.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes validation annotations from Jakarta Bean Validation to ensure the integrity of the transferred data.
 *
 * <p>Fields:
 * @param id Doctor's ID.
 * @param name Doctor's name.
 * @param dni Doctor's dni.
 * @param address Doctor's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record DoctorUpdateData(
        @NotNull(message = "ID is required.")
        Long id,
        @NotNull(message = "Name is required.")
        String name,
        @NotBlank(message = "Email is required.")
        @Email
        String email,
        @NotBlank(message = "Phone number is required.")
        @Size(min = 0, max = 12)
        String phoneNumber,
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d|\\d\\.\\d{3}\\.\\d{3}\\-\\d",
                message = "Invalid DNI format.")
        String dni,
        @NotNull(message = "Address is required.")
        @Valid
        AddressData address) {

}
