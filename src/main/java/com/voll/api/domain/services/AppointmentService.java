package com.voll.api.domain.services;

// IMPORTS.
import com.voll.api.domain.dto.appointment.AppointmentDetailData;
import com.voll.api.domain.dto.appointment.CancelAppointmentData;
import com.voll.api.domain.dto.appointment.ReserveAppointmentData;
import com.voll.api.domain.models.Appointment;
import com.voll.api.domain.models.Doctor;
import com.voll.api.domain.validations.appointments.AttentionValidator;
import com.voll.api.domain.validations.appointments.CancellationValidator;
import com.voll.api.infrastructure.config.exception.IntegrityValidation;
import com.voll.api.repository.AttentionRepository;
import com.voll.api.repository.DoctorRepository;
import com.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * THIS SERVICE CLASS HANDLING APPOINTMENT RESERVATION AND CANCELLATION OPERATIONS.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Service
public class AppointmentService {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AttentionRepository attentionRepository;
    @Autowired
    List<AttentionValidator> validators;
    @Autowired
    List<CancellationValidator> cancelValidators;

    /**
     * RESERVES AN APPOINTMENT BASED ON THE PROVIDED DATA.
     *
     * @param data The data for reserving the appointment.
     * @return Details of the reserved appointment.
     * @throws IntegrityValidation If there are validation errors or the operation fails.
     */
    public AppointmentDetailData reserveAppointment(ReserveAppointmentData data) {
        // VALIDATE PATIENT EXISTENCE.
        if(!patientRepository.findById(data.idPatient()).isPresent()){
            throw new IntegrityValidation("ID not found.");
        }
        // VALIDATE DOCTOR EXISTENCE.
        if(data.idDoctor()!=null && doctorRepository.existsById(data.idDoctor())) {
            throw new IntegrityValidation("ID not found.");
        }
        // RUN APPOINTMENT VALIDATION LOGIC.
        validators.forEach(v->v.validate(data));
        // FETCH PATIENT AND SELECT AN AVAILABLE DOCTOR.
        var patient = patientRepository.findById(data.idPatient()).get();
        var doctor = selectDoctor(data);
        if(doctor==null) {
            throw new IntegrityValidation(("There are no doctors available for this time and speciality."));
        }
        // CREATE AND SAVE THE APPOINTMENT.
        var appointment = new Appointment(doctor,patient,data.date());
        attentionRepository.save(appointment);
        return new AppointmentDetailData(appointment);
    }

    /**
     * SELECTS AN AVAILABLE DOCTOR BASED ON THE PROVIDED DATA.
     *
     * @param data The data containing doctor and speciality information.
     * @return The selected doctor.
     * @throws IntegrityValidation If no doctor is available for the specified speciality and date.
     */
    private Doctor selectDoctor(ReserveAppointmentData data) {
        if(data.idDoctor()!=null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.speciality() == null) {
            throw new IntegrityValidation("A specialty must be selected for the doctor.");
        }
        return doctorRepository.selectDoctorWithSpecialityDate(data.speciality(), data.date());
    }

    /**
     * CANCELS AN EXISTING APPOINTMENT BASED ON THE PROVIDED DATA.
     *
     * @param data The data for canceling the appointment.
     * @return Details of the canceled appointment.
     * @throws IntegrityValidation If there are validation errors or the operations fails.
     */
    public AppointmentDetailData cancelAppointment(CancelAppointmentData data) {
        // VALIDATE APPOINTMENT EXISTENCE.
        if (!attentionRepository.existsById(data.id())) {
            throw new IntegrityValidation("Appointment not found.");
        }
        // RUN CANCELLATION VALIDATION LOGIC.
        cancelValidators.forEach(v -> v.validate(data));
        // CANCEL THE APPOINTMENT AND SAVE THE CHANGES.
        var appointment = attentionRepository.getReferenceById(data.id());
        appointment.cancel(data.reason());
        attentionRepository.save(appointment);
        return new AppointmentDetailData(appointment);
    }
}
