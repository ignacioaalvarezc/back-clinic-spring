package chi.voll.api.domain.patient;

public record PatientListData(Long id, String nombre, String email, String documento) {

    public PatientListData(Patient patient) {
        this(patient.getId(), patient.getNombre(), patient.getEmail(), patient.getDocumento());
    }
}
