package com.voll.api.domain.models;

import com.voll.api.domain.dto.patient.PatientRecordData;
import com.voll.api.domain.dto.patient.PatientUpdateData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Address address;
    private Boolean activo;

    public Patient(PatientRecordData datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.address = new Address(datos.direccion());
    }

    public void updateData(PatientUpdateData datos) {
        if (datos.nombre() != null) {
            this.nombre = datos.nombre();
        }
        if (datos.telefono() != null) {
            this.telefono = datos.telefono();
        }
        if (datos.direccion() != null) {
            this.address.actualizarDireccion(datos.direccion());
        }
    }

    public void delete() { this.activo = false; }
}
