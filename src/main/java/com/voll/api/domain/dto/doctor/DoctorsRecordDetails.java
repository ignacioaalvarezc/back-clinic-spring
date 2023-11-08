package com.voll.api.domain.dto.doctor;
// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;
import com.voll.api.domain.models.enumeration.Speciality;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING DETAILED INFORMATION FOR CREATING OR UPDATING A DOCTOR RECORD.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes validation annotations from Jakarta Bean Validation to ensure the integrity of the transferred data.
 * It encapsulates necessary information such as:
 * @param name Doctor's name.
 * @param email Doctor's email.
 * @param phoneNumber Doctor's phone number.
 * @param dni Doctor's dni.
 * @param speciality Doctor's speciality.
 * @param address Doctor's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record DoctorsRecordDetails(
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Email is required.")
        @Email
        String email,
        @NotBlank(message = "Phone number is required.")
        @Size(min = 0, max = 12)
        String phoneNumber,
        @NotBlank(message = "DNI is required.")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d|\\d\\.\\d{3}\\.\\d{3}\\-\\d",
                message = "Invalid DNI format.")
        String dni,
        @NotBlank(message = "Speciality is required.")
        Speciality speciality,
        @NotNull(message = "Address is required.")
        @Valid
        AddressData address) {
}