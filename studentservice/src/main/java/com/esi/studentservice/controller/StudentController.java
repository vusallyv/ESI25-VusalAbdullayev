package com.esi.studentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.studentservice.dto.StudentDto;
import com.esi.studentservice.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<String> addStudentInfo(@RequestBody StudentDto studentDto) {
        studentService.addStudentInfo(studentDto);
        return ResponseEntity.ok("Student info received");
    }

    // Task 4

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> fetchStudentData(@PathVariable String id) {
        try {
            StudentDto studentDto = studentService.fetchStudentData(id);
            return ResponseEntity.ok(studentDto);
        } catch (RuntimeException e) {
            log.error("Error in fetchStudentData: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
