package com.esi.teacherservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.esi.teacherservice.dto.AppointmentDto;
import com.esi.teacherservice.model.Appointment;
import com.esi.teacherservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @KafkaListener(topics = "appointmentTopic", groupId = "teacher-service-group")
    public void consumeAppointment(AppointmentDto appointmentDto) {
        log.info("Received appointment event: {}", appointmentDto);

        Appointment appointment = Appointment.builder()
                .appointmentid(appointmentDto.getAppointmentid())
                .studentId(appointmentDto.getStudentId())
                .studentName(appointmentDto.getStudentName())
                .teacherId(appointmentDto.getTeacherId())
                .date(appointmentDto.getDate())
                .build();

        teacherRepository.save(appointment);
        log.info("Appointment saved to teacher service database: {}", appointment);
    }
}
