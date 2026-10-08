package com.hospital.hospital_managment.controller;

import com.hospital.hospital_managment.model.Doctor;
import com.hospital.hospital_managment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String specialization) {
        return doctorService.getDoctorsBySpecialization(specialization);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return "Doctor deleted successfully with id: " + id;
    }
}