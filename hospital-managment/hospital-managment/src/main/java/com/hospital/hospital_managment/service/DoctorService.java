package com.hospital.hospital_managment.service;

import com.hospital.hospital_managment.model.Doctor;
import com.hospital.hospital_managment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Specialization se search karne ke liye
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }

    // Doctor update karne ke liye
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setAvailableDays(updatedDoctor.getAvailableDays());
            doctor.setConsultationFee(updatedDoctor.getConsultationFee());
            return doctorRepository.save(doctor);
        }).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    // Doctor delete karne ke liye
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}