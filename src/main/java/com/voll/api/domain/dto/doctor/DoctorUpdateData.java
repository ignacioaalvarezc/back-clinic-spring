package com.voll.api.domain.dto.doctor;

// IMPORTS.
import com.voll.api.domain.dto.address.AddressData;
import jakarta.validation.constraints.NotNull;

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
        @NotNull(message = "DNI is required.")
        String dni,
        AddressData address) {

}
