package com.voll.api.domain.dto.doctor;

// IMPORTS.

import com.voll.api.domain.models.Address;
import com.voll.api.domain.models.Doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING SIMPLIFIED DOCTOR INFORMATION FOR LISTING PURPOSES.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes a specialized constructor to transform a doctor entity into this DTO format.
 * It encapsulates necessary information such as:
 * @param id Doctor's ID.
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
public record DoctorListData(
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
		@NotBlank(message = "Speciality number is required.")
		String speciality,
		@NotBlank(message = "DNI is required.")
		@Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d|\\d\\.\\d{3}\\.\\d{3}\\-\\d",
				message = "Invalid DNI format.")
		String dni,
		@NotNull(message = "Address is required.")
		@Valid
		Address address) {

	/**
	 * CONSTRUCTS A DOCTOR LIST DATA OBJECT BASED ON THE PROVIDED DOCTOR ENTITY.
	 *
	 * @param doctor The doctor entity from which data is extracted.
	 */
	public DoctorListData(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.getDni(), doctor.getSpeciality().toString(), doctor.getAddress());
	}
	
}
