package com.esi.studentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studenttable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String studentData;
}
