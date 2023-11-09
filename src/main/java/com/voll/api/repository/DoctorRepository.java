package com.voll.api.repository;

// IMPORTS.
import com.voll.api.domain.models.Doctor;
import com.voll.api.domain.models.enumeration.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * JPA REPOSITORY INTERFACE FOR MANAGING DOCTOR ENTITIES IN THE DATABASE.
 * Extends JpaRepository providing CRUD operations and custom query methods for the Doctor entity.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    /**
     * RETRIEVES A PAGE OF ACTIVE DOCTORS FROM THE DATABASE.
     *
     * @param pagination The pagination information.
     * @return A page of active doctors.
     */
    Page<Doctor> findByStatusTrue(Pageable pagination);

    /**
     * SELECTS A DOCTOR WITH A SPECIFIC SPECIALITY AND AVAILABILITY ON GIVEN DATA.
     *
     * @param speciality The speciality of the doctor.
     * @param date The specific date and time for the appointment.
     * @return A doctor with the specified speciality and availability on the given date.
     */
    @Query("""
            select d from Doctor d
            where d.status = true and
            d.speciality=:speciality and
            d.id not in(
            select a.doctor.id from Appointment a
            where a.date=:date
            )
            order by rand()
            limit 1
            """)
    Doctor selectDoctorWithSpecialityDate(Speciality speciality, LocalDateTime date);

    /**
     * RETRIEVES THE STATUS OF A DOCTOR BY THEIR ID.
     *
     * @param idDoctor The ID of the doctor.
     * @return The status of the specified doctor.
     */
    @Query("""
            select d.status
            from Doctor d
            where d.id=:idDoctor
            """)
    Boolean findStatusById(Long idDoctor);
}
