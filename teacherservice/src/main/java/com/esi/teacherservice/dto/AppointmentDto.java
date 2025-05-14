package com.esi.teacherservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private String appointmentid;
    private String studentId;
    private String studentName;
    private String studentData;
    private String teacherId;
    private String date;
}
