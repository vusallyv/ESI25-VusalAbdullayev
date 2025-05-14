package com.esi.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.studentservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
}
