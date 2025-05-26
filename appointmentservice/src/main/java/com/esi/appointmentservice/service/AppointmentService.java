package com.esi.appointmentservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.esi.appointmentservice.dto.AppointmentDto;
import com.esi.appointmentservice.model.Appointment;
import com.esi.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final KafkaTemplate<String, AppointmentDto> kafkaTemplate;

    public void appointmentBooked(AppointmentDto appointmentDto) {
        // Map AppointmentDto to Appointment entity
        Appointment appointment = Appointment.builder()
                .appointmentid(appointmentDto.getAppointmentid())
                .studentId(appointmentDto.getStudentId())
                .studentName(appointmentDto.getStudentName())
                .teacherId(appointmentDto.getTeacherId())
                .date(appointmentDto.getDate())
                .build();

        // Save the appointment to the database
        appointmentRepository.save(appointment);

        // Send the appointment event to the teacher service via Kafka
        kafkaTemplate.send("appointmentTopic", appointmentDto);
        log.info("Appointment event sent to teacher service: {}", appointmentDto);
    }
}
