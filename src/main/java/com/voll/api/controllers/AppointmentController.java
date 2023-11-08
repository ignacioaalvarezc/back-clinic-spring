package com.voll.api.controllers;

// IMPORTS.
import com.voll.api.domain.dto.appointment.CancelAppointmentData;
import com.voll.api.domain.services.AppointmentService;
import com.voll.api.domain.dto.appointment.ReserveAppointmentData;
import com.voll.api.infrastructure.config.exception.IntegrityValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * CONTROLLER CLASS RESPONSIBLE FOR HANDLING MEDICAL ATTENTION RESERVATIONS.
 * This controller provides an endpoint to register medical consultations in the database.
 * Operations are protected by bearer token authentication.
 *
 * @author Ignacio Álvarez
 * @version 1.0
 * @since 2023-11-07
 */
@Controller
@ResponseBody
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    // DEPENDENCY INJECTIONS.
    @Autowired
    private AppointmentService appointmentService;

    /**
     * ENDPOINT TO REGISTER A MEDICAL CONSULTATION IN THE DATABASE.
     *
     * @param data The data for the medical attention reservation.
     * @throws IntegrityValidation Exception if there are validation or integrity issues with data.
     * @return ResponseEntity containing the response from the reservation service.
     */
    @PostMapping
    @Transactional
    @Operation(
            summary = "Register an attention in the database",
            description = "Registers a new medical consultation based on the provided data",
            tags = { "appointment", "post" })
    public ResponseEntity reserveAppointment(@RequestBody
                                             @Valid
                                             ReserveAppointmentData data)
                                                throws IntegrityValidation {
        var response = appointmentService.reserveAppointment(data);
        return ResponseEntity.ok(response);
    }

    /**
     * ENDPOINT TO CANCEL A MEDICAL CONSULTATION IN THE DATABSE.
     *
     * @param id Appointment's ID.
     * @param data Information for canceling the medical appointment.
     * @return ResponseEntity containing the response from the cancellation service.
     * @throws IntegrityValidation Exception if there are validation or integrity problems with the data.
     */
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary= "Cancel an appointment in the database",
            description = "Cancels an existing medical consultation based on the provided data",
            tags = { "appointment", "delete" })
    public ResponseEntity cancelAppointment(@PathVariable Long id,
                                            @RequestBody
                                            @Valid
                                            CancelAppointmentData data)
                                                throws IntegrityValidation {
        var response = appointmentService.cancelAppointment(data);
        return ResponseEntity.ok(response);
    }
    )
}
