package com.voll.api.domain.dto.patient;

// IMPORTS.

import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING PATIENT INFORMATION FOR UPDATE OPERATIONS.
 * This DTO class encapsulates the data needed to update a patient's details.
 * It includes a specialized constructor to transform a patient entity into this DTO format.
 *
 * <p>Fields:
 * @param id Patient's ID.
 * @param name Patient's name.
 * @param email Patient's email.
 * @param phoneNumber Patient's phone number.
 * @param address Patient's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record PatientUpdateData(
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
        @NotNull(message = "Address is required.")
        @Valid
        AddressData address) {
}
