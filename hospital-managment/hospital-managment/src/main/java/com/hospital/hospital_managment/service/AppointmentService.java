package com.hospital.hospital_managment.service;

import com.hospital.hospital_managment.model.Appointment;
import com.hospital.hospital_managment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointment(Appointment appointment) {
        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
            appointment.setStatus("BOOKED");
        }
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}