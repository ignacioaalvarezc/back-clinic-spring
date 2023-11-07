package chi.voll.api.domain.patient;

import chi.voll.api.domain.direccion.Address;

public record DoctorsRecordDetails(Long id, String nombre, String email, String documento, String telefono, Address address) {

    public DoctorsRecordDetails(Patient patient) {
        this(patient.getId(), patient.getNombre(), patient.getEmail(), patient.getDocumento(), patient.getTelefono(), patient.getAddress());
    }
}
