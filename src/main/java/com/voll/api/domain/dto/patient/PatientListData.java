package com.voll.api.domain.dto.patient;

// IMPORTS.

import com.voll.api.domain.models.Address;
import com.voll.api.domain.models.Patient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING SIMPLIFIED INFORMATION FOR LISTING PURPOSES.
 * This DTO class follows Java records syntax, providing a concise representation of data.
 * It includes a specialized constructor to transform a patient entity into this DTO format.
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
public record PatientListData(
        @NotBlank(message = "ID is required.")
        Long id,
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
        @NotNull(message = "Address is required.")
        @Valid
        Address address) {

    /**
     * CONSTRUCTS A PATIENT LIST DATA OBJECT BASED ON THE PROVIDED PATIENT ENTITY.
     *
     * @param patient The patient entity from which data is extracted.
     */
    public PatientListData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhoneNumber(), patient.getDni(), patient.getAddress());
    }

}
