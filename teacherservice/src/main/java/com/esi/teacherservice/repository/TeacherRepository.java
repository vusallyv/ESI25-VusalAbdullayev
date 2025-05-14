package com.esi.teacherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.teacherservice.model.Appointment;

public interface TeacherRepository extends JpaRepository<Appointment, String> {
}
