package com.esi.appointmentservice.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointmenttable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    private String appointmentid;
    private String studentId;
    private String studentName;
    private String teacherId;
    private String date;

}
