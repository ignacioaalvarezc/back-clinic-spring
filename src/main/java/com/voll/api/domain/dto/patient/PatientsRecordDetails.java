package com.voll.api.domain.dto.patient;

// IMPORTS.
import com.voll.api.domain.models.Address;
import com.voll.api.domain.models.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING DETAILED INFORMATION ABOUT A PATIENT.
 * This DTO class provides a structured view of patient data for data transfer operations.
 * It includes validation annotations from Jakarta Bean Validation to ensure the integrity of the transferred data.
 *
 * <p>Fields:
 * @param id Patient's ID.
 * @param name Patient's name.
 * @param email Patient's email.
 * @param dni Patient's dni.
 * @param phoneNumber Patient's phone number.
 * @param address Patient's address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record PatientsRecordDetails(
        @NotBlank(message = "ID is required.")
        Long id,
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Email is required.")
        String email,
        @NotBlank(message = "DNI is required.")
        @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d|\\d\\.\\d{3}\\.\\d{3}\\-\\d",
                message = "Invalid DNI format.")
        String dni,
        @NotBlank(message = "Phone number is required.")
        @Size(min = 0, max = 12)
        String phoneNumber,
        @NotBlank(message = "Address is required.")
        Address address) {

    /**
     * CONSTRUCTOR THAT MAPS FIELDS FROM A PATIENT ENTITY TO INITIALIZE THE DTO FIELDS.
     *
     * @param patient The patient entity containing the required information.
     */
    public PatientsRecordDetails(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getDni(), patient.getPhoneNumber(), patient.getAddress());
    }
}
