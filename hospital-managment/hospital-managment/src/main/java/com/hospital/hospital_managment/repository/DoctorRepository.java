package com.hospital.hospital_managment.repository;

import com.hospital.hospital_managment.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Specialization ke basis par doctors search karne ke liye
    List<Doctor> findBySpecializationIgnoreCase(String specialization);
}