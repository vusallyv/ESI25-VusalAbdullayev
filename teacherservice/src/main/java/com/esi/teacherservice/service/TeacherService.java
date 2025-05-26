package com.esi.teacherservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.teacherservice.dto.AppointmentDto;
import com.esi.teacherservice.dto.StudentDto;
import com.esi.teacherservice.model.Appointment;
import com.esi.teacherservice.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final WebClient webClient;

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

    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = teacherRepository.findAll();

        return appointments.stream()
                .<AppointmentDto>map(appointment -> {
                    StudentDto studentDto = webClient.get()
                            .uri("/student/{id}", appointment.getStudentId())
                            .retrieve()
                            .bodyToMono(StudentDto.class)
                            .block(); // Block to get the result synchronously

                    return AppointmentDto.builder()
                            .appointmentid(appointment.getAppointmentid())
                            .studentId(appointment.getStudentId())
                            .studentName(studentDto.getStudentName())
                            .studentData(studentDto.getStudentData())
                            .teacherId(appointment.getTeacherId())
                            .date(appointment.getDate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
