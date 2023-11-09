package com.voll.api.controllers;

// IMPORTS
import com.voll.api.domain.dto.address.AddressData;
import com.voll.api.domain.dto.doctor.DoctorListData;
import com.voll.api.domain.dto.doctor.DoctorUpdateData;
import com.voll.api.domain.dto.doctor.DoctorsRecordDetails;
import com.voll.api.domain.dto.doctor.DoctorsResponseData;
import com.voll.api.domain.models.Doctor;
import com.voll.api.repository.DoctorRepository;
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
import java.net.URI;

/**
 *  CONTROLLER TO HANDLE OPERATIONS RELATED TO THE DATA FOR EACH DOCTOR.
 *  This controller provides endpoints to access and manipulate information from doctor,
 *  including querying information, creating a new records, updating and deleting existing data.
 *  Operations are protected by bearer token authentication.
 *  Responses are returned in JSON format for easy processing by client applications.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

	// DEPENDENCY INJECTIONS.
	@Autowired
	private DoctorRepository doctorRepository;

	/**
	 * ENDPOINT TO REGISTER A NEW DOCTOR IN THE DATABASE.
	 *
	 * @param doctorsRecordDetails The details of the new doctor to be registered.
	 * @param uriComponentsBuilder Helps in building of uniforms resource identifiers(URIs) for the newly created resource.
	 * @return ResponseEntity containing the newly created doctor's data.
	 */
    @PostMapping
	@Transactional
	@Operation(summary = "Register a new doctor in the database.")
    public ResponseEntity<DoctorsResponseData> saveDoctor(@RequestBody @Valid DoctorsRecordDetails doctorsRecordDetails,
														  UriComponentsBuilder uriComponentsBuilder) {
    	Doctor doctor = doctorRepository.save(new Doctor(doctorsRecordDetails));
    	DoctorsResponseData doctorsResponseData = new DoctorsResponseData(doctor.getId(), doctor.getName(), doctor.getDni(),
    			doctor.getEmail(), doctor.getPhoneNumber(), doctor.getSpeciality(),
    			new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(),
    					doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
    					doctor.getAddress().getComplement()));
    	URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(doctor.getId()).toUri();
    	return ResponseEntity.created(url).body(doctorsResponseData);
    }

	/**
	 * ENDPOINT TO RETRIEVE A PAGINATED LIST OF DOCTORS.
	 *
	 * @param pagination Pagination parameter.
	 * @return ResponseEntity containing the paginated list of doctors.
	 */
	@GetMapping
	@Operation(summary = "Get the list of doctors.")
    public ResponseEntity<Page<DoctorListData>> DoctorsList(@PageableDefault(size = 2) Pageable pagination
	) {
	   	//return medicoRepository.findAll(pagination).map(DoctorListData::new);
    	return ResponseEntity.ok(doctorRepository.findByStatusTrue(pagination).map(DoctorListData::new));

    }

	/**
	 *  ENDPOINT TO UPDATE AN EXISTING DOCTOR'S DATA,
	 *
	 * @param doctorUpdateData The updated data for the doctor.
	 * @return ResponseEntity containing the updated doctor's data.
	 */
	@PutMapping
    @Transactional
	@Operation(summary = "Updates the data of an existing doctor.")
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateData doctorUpdateData) {
    	Doctor doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
    	doctor.updateData(doctorUpdateData);
    	return ResponseEntity.ok(new DoctorsResponseData(doctor.getId(), doctor.getName(), doctor.getDni(),
    			doctor.getEmail(), doctor.getPhoneNumber(), doctor.getSpeciality(),
    			new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(),
    					doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
    					doctor.getAddress().getComplement())));
    }

	/**
	 * ENDPOINT TO REMOVE A REGISTERED DOCTOR (REMAINS INACTIVE).
	 *
	 * @param id The ID of the doctor to be deleted.
	 * @return ResponseEntity indicating a successful deletion.
	 */
	@DeleteMapping("/{id}")
    @Transactional
	@Operation(summary = "Removes a registered doctor. Remains inactive.")
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
    	Doctor doctor = doctorRepository.getReferenceById(id);
    	doctor.disableDoctor();
    	return ResponseEntity.noContent().build();
    }

	/**
	 *  ENDPOINT TO RETRIEVE A SPECIFIC DOCTOR'S DATA BY ID.
	 *
	 * @param id The ID of the doctor to retrieve.
	 * @return ResponseEntity containing the specified doctor's data.
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Get the doctors records according to id.")
    public ResponseEntity<DoctorsResponseData> returnDoctorData(@PathVariable Long id) {
    	Doctor doctor = doctorRepository.getReferenceById(id);
    	var doctorData = new DoctorsResponseData(doctor.getId(), doctor.getName(), doctor.getDni(),
    			doctor.getEmail(), doctor.getPhoneNumber(), doctor.getSpeciality(),
    			new AddressData(doctor.getAddress().getStreet(), doctor.getAddress().getDistrict(),
    					doctor.getAddress().getCity(), doctor.getAddress().getNumber(),
    					doctor.getAddress().getComplement()));
    	return ResponseEntity.ok(doctorData);
    }
}
