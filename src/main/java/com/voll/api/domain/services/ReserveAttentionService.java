package com.voll.api.domain.services;

import com.voll.api.domain.dto.appointment.DatosDetalleConsulta;
import com.voll.api.domain.dto.appointment.ReserveAttentionData;
import com.voll.api.domain.models.Appointment;
import com.voll.api.domain.models.Doctor;
import com.voll.api.repository.DoctorRepository;
import com.voll.api.repository.PatientRepository;
import com.voll.api.infrastructure.config.exception.IntegrityValidation;
import com.voll.api.domain.validations.appointments.AttentionValidator;
import com.voll.api.repository.AttentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveAttentionService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AttentionRepository attentionRepository;

    @Autowired
    List<AttentionValidator> validadores;

    public DatosDetalleConsulta reserve(ReserveAttentionData datos) {
        if(!patientRepository.findById(datos.idPatient()).isPresent()){
            throw new IntegrityValidation("Este id para el paciente no fue encontrado");
        }
        if(datos.idDoctor()!=null && doctorRepository.existsById(datos.idDoctor())) {
            throw new IntegrityValidation("Este id para el medico no fue encontrado");
        }

        validadores.forEach(v->v.validate(datos));

        var paciente = patientRepository.findById(datos.idPatient()).get();
        var medico = seleccionarMedico(datos);
        if(medico==null) {
            throw new IntegrityValidation(("No existen medicos disponibles para este horario y especialidad."));
        }
        var consulta = new Appointment(medico,paciente,datos.date());
        attentionRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }
    /*
    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionDeIntegridad("Id de la consulta informado no existe.");
        }
        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }*/

    private Doctor seleccionarMedico(ReserveAttentionData datos) {
        if(datos.idDoctor()!=null){
            return doctorRepository.getReferenceById(datos.idDoctor());
        }
        if (datos.speciality() == null) {
            throw new IntegrityValidation("Debe seleccionarse una especialidad para el meédico.");
        }
        return doctorRepository.selectDoctorWithSpecialityDate(datos.speciality(), datos.date());
    }
}
