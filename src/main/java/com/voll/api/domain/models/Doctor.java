package com.voll.api.domain.models;

// IMPORTS
import com.voll.api.domain.dto.doctor.DoctorUpdateData;
import com.voll.api.domain.dto.doctor.DoctorsRecordDetails;
import jakarta.persistence.*;
import lombok.*;

/**
 * THIS MODEL CLASS REPRESENTS A DOCTOR ENTITY.
 * This models the properties and behavior of doctors, including their personal details,
 * status, speciality and address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dni;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private com.voll.api.domain.models.enumeration.Speciality Speciality;
    @Embedded
    private Address address;

    /**
     * CONSTRUCTS A DOCTOR OBJECT BASED ON THE PROVIDES DOCTORS RECORD DETAILS DTO.
     *
     * @param doctorsRecordDetails The DTO containing details of the doctor.
     */
    public Doctor(DoctorsRecordDetails doctorsRecordDetails) {
    	this.status = true;
    	this.name = doctorsRecordDetails.name();
    	this.email = doctorsRecordDetails.email();
    	this.phoneNumber = doctorsRecordDetails.phoneNumber();
    	this.dni = doctorsRecordDetails.dni();
    	this.Speciality = doctorsRecordDetails.speciality();
    	this.address = new Address(doctorsRecordDetails.address());
    }

    /**
     * UPDATES THE DOCTOR'S DATA BASED ON THE PROVIDED DOCTOR UPDATE DATA DTO.
     *
     * @param doctorUpdateData The DTO containing updated doctor'2 data.
     */
    public void updateData(DoctorUpdateData doctorUpdateData) {
    	if (doctorUpdateData.name() != null) {
    		this.name = doctorUpdateData.name();
    	}
    	if (doctorUpdateData.dni() != null) {
    		this.dni = doctorUpdateData.dni();
    	}
    	if (doctorUpdateData.address() != null) {
        	this.address = address.updateAddress(doctorUpdateData.address());
    	}    			
    }

    /**
     * DISABLES THE DOCTOR, SETTING THE STATUS TO INACTIVE.
     */
    public void disableDoctor() {
    	this.status = false;
    }

}
