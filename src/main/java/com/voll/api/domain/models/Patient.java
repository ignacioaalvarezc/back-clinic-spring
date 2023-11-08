package com.voll.api.domain.models;

// IMPORTS.
import com.voll.api.domain.dto.patient.PatientRecordData;
import com.voll.api.domain.dto.patient.PatientUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * THIS MODEL CLASS REPRESENTS A PATIENT IN THE APPLICATION.
 * This models the properties and behavior of patients, including their personal details,
 * status and address.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String dni;
    @Embedded
    private Address address;
    private Boolean status;

    /**
     * CONSTRUCTS A DOCTOR OBJECT BASED ON THE PROVIDED PATIENT RECORDS DATA DTO.
     *
     * @param data The DTO containing details of the doctor.
     */
    public Patient(PatientRecordData data) {
        this.status = true;
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.dni = data.dni();
        this.address = new Address(data.address());
    }

    /**
     * UPDATES THE DOCTOR'S DATA BASED ON THE PROVIDED PATIENT UPDATE DATA DTO.
     *
     * @param data The DTO containing updated doctor's data.
     */
    public void updateData(PatientUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phoneNumber() != null) {
            this.phoneNumber = data.phoneNumber();
        }
        if (data.address() != null) {
            this.address.updateAddress(data.address());
        }
    }

    /**
     * DISABLES THE PATIENT, SETTING THE STATUS TO INACTIVE.
     */
    public void delete() { this.status = false; }
}
