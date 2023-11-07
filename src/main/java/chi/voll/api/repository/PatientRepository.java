package chi.voll.api.repository;

import chi.voll.api.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusTrue(Pageable paginacion);

    @Query("""
            select p.activo
            from Paciente p
            where p.id=:idPaciente
            """)
    Boolean findActivoById(Long idPaciente);
}
