package chi.voll.api.domain.medico;

public record DoctorListData(Long id, String nombre, String especialidad, String documento, String email) {

	public DoctorListData(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getSpeciality().toString(), doctor.getDni(), doctor.getEmail());
	}
	
}
