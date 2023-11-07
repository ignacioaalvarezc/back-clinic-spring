package chi.voll.api.repository;

import chi.voll.api.domain.medico.Doctor;
import chi.voll.api.domain.medico.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Page<Doctor> findByStatusTrue(Pageable paginacion);

    @Query("""
            select d from Doctor d
            where d.status = true and
            d.speciality=:speciality and
            d.id not in(
            select c.doctor.id from Consulta c
            where c.fecha=:fecha
            )
            order by rand()
            limit 1
            """)
    Doctor selectDoctorWithSpecialityDate(Speciality speciality, LocalDateTime date);

    @Query("""
            select d.status
            from Doctor d
            where d.id=:idDoctor
            """)
    Boolean findStatusById(Long idDoctor);
}
