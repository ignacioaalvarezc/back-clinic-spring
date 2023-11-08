package com.voll.api.domain.dto.patient;

// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING PATIENT INFORMATION CREATING A NEW PATIENT RECORDS.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes validation annotations from Jakarta Bean Validation to ensure the integrity of the transferred data.
 *
 * <p>Fields:
 * @param name Patient's name.
 * @param email Patient's email.
 * @param phoneNumber Patient's phone number.
 * @param dni Patient's DNI.
 * @param address Patient's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record PatientRecordData(
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Email is required.")
        @Email
        String email,
        @NotBlank(message = "Phone number is required.")
        @Size(min = 0, max = 15)
        String phoneNumber,
        @NotBlank(message = "DNI is required.")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d|\\d\\.\\d{3}\\.\\d{3}\\-\\d",
                message = "Invalid DNI format.")
        String dni,
        @NotNull(message = "Address is required.")
        @Valid
        AddressData address) {
}
