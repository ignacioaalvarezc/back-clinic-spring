package com.voll.api.domain.models;

import com.voll.api.domain.dto.patient.PatientRecordData;
import com.voll.api.domain.dto.patient.PatientUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
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
    private Boolean activo;

    public Patient(PatientRecordData datos) {
        this.activo = true;
        this.name = datos.name();
        this.email = datos.email();
        this.phoneNumber = datos.phoneNumber();
        this.dni = datos.dni();
        this.address = new Address(datos.address());
    }

    public void updateData(PatientUpdateData datos) {
        if (datos.name() != null) {
            this.name = datos.name();
        }
        if (datos.phoneNumber() != null) {
            this.phoneNumber = datos.phoneNumber();
        }
        if (datos.address() != null) {
            this.address.updateAddress(datos.address());
        }
    }

    public void delete() { this.activo = false; }
}
