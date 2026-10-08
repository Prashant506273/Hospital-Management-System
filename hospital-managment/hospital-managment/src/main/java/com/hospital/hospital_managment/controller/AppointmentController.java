package com.hospital.hospital_managment.controller;

import com.hospital.hospital_managment.model.Appointment;
import com.hospital.hospital_managment.model.Doctor;
import com.hospital.hospital_managment.repository.AppointmentRepository;
import com.hospital.hospital_managment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody Appointment appointment) {
        try {
            // Set default status if missing or null
            if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
                appointment.setStatus("BOOKED");
            }

            // Link existing Doctor from DB
            if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null) {
                Doctor doc = doctorRepository.findById(appointment.getDoctor().getId()).orElse(null);
                if (doc == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Selected Doctor Not Found!");
                }
                appointment.setDoctor(doc);
            }

            Appointment saved = appointmentRepository.save(appointment);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error booking appointment: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}