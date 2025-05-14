package com.esi.appointmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.appointmentservice.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
