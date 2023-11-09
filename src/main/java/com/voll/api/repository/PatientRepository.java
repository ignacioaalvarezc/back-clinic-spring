package com.voll.api.repository;

// IMPORTS.
import com.voll.api.domain.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPA REPOSITORY INTERFACE OFR MANAGING PATIENT ENTITIES IN THE DATABASE.
 * Extends JpaRepository providing CRUD operations and custom query methods for the patient entity.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * RETRIEVES A PAGE OF ACTIVE PATIENTS FROM THE DATABASE.
     *
     * @param pagination The pagination information.
     * @return A page of active patients.
     */
    Page<Patient> findAllByStatusTrue(Pageable pagination);

    /**
     * RETRIEVES THE STATUS OF A PATIENT BY THEIR ID.
     *
     * @param idPatient The ID of the patient.
     * @return The status of the specified patient.
     */
    @Query("""
            select p.status
            from Patient p
            where p.id=:idPatient
            """)
    Boolean findStatusById(Long idPatient);
}
