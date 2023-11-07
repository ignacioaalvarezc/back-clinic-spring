package com.voll.api.controllers;
// IMPORTS.
import chi.voll.api.domain.patient.*;
import com.voll.api.domain.dto.patient.PatientListData;
import com.voll.api.domain.dto.patient.PatientRecordData;
import com.voll.api.domain.dto.patient.PatientUpdateData;
import com.voll.api.domain.dto.patient.PatientsRecordDetails;
import com.voll.api.domain.models.Patient;
import com.voll.api.domain.patient.*;
import com.voll.api.repository.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * CONTROLLER CLASS RESPONSIBLE FOR MANAGING PATIENT-RELATED OPERATIONS.
 * This controller provides endpoints to register, list, update, delete and retrieve details of patients.
 * Operations are secured by bearer token authentication.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class PatientController {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private PatientRepository patientRepository;

    /**
     * ENDPOINT TO REGISTER A NEW PATIENT.
     *
     * @param data The data for the new patient.
     * @param uriBuilder Helps in building of uniforms resource identifiers(URIs) for the newly created resource.
     * @return ResponseEntity containing the newly created patient's details.
     */
    @PostMapping
    @Transactional
    @Operation(summary = "Register a new patient")
    public ResponseEntity savePatient(@RequestBody @Valid PatientRecordData data,
                                      UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientsRecordDetails(patient));
    }

    /**
     * ENDPOINT TO LIST PATIENTS WITH PAGINATION.
     *
     * @param pagination Pagination parameters.
     * @return ResponseEntity containing a paginated list of patients.
     */
    @GetMapping
    @Operation(summary = "Get the list for the patients")
    public ResponseEntity<Page<PatientListData>> listPatients(@PageableDefault
                                                               (size = 10, sort = {"name"}) Pageable pagination) {
        var page = patientRepository.findAllByStatusTrue(pagination).map(PatientListData::new);
        return ResponseEntity.ok(page);
    }

    /**
     * ENDPOINT TO UPDATE PATIENT INFORMATION
     *
     * @param data Updated patient data.
     * @return ResponseEntity containing the updated patient's details.
     */
    @PutMapping
    @Transactional
    @Operation(summary = "Update patient information")
    public ResponseEntity updatePatient(@RequestBody @Valid PatientUpdateData data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateData(data);
        return ResponseEntity.ok(new PatientsRecordDetails(patient));
    }

    /**
     * ENDPOINT TO DELETE A PATIENT BY ID.
     *
     * @param id The ID of the patient to be deleted.
     * @return ResponseEntity indicating a successful deletion.
     */
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a patient according to ID")
    public ResponseEntity deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    /**
     * ENDPOINT TO RETRIEVE PATIENT DETAILS BY ID.
     *
     * @param id The ID of the patient to retrieve details for.
     * @return ResponseEntity containing the specified patient's details.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Gets the details for the patient with the given ID")
    public ResponseEntity detailsPatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientsRecordDetails(patient));
    }
}
