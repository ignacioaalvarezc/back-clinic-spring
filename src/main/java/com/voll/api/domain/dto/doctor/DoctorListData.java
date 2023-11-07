package com.voll.api.domain.dto.doctor;

// IMPORTS.
import com.voll.api.domain.models.Doctor;

/**
 * THIS RECORD CLASS IS A DATA TRANSFER OBJECT REPRESENTING SIMPLIFIED DOCTOR INFORMATION FOR LISTING PURPOSES.
 * This DTO class follows Java record syntax, providing a concise representation of data.
 * It includes a specialized constructor to transform a doctor entity into this DTO format.
 * It encapsulates essential details such as:
 * @param id Doctor's ID.
 * @param name Doctor's name.
 * @param speciality Doctor's speciality.
 * @param dni Doctor's dni.
 * @param email Doctor's email.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public record DoctorListData(Long id, String name, String speciality, String dni, String email) {

	/**
	 * CONSTRUCTS A DOCTOR LIST DATA OBJECT BASED ON THE PROVIDED DOCTOR ENTITY.
	 *
	 * @param doctor The doctor entity from which data is extracted.
	 */
	public DoctorListData(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getSpeciality().toString(), doctor.getDni(), doctor.getEmail());
	}
	
}
