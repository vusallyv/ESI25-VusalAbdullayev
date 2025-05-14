package com.esi.appointmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.appointmentservice.dto.AppointmentDto;
import com.esi.appointmentservice.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<String> appointmentBooked(@RequestBody AppointmentDto appointmentDto) {
        appointmentService.appointmentBooked(appointmentDto);
        return ResponseEntity.ok("An appointment has been recieved");

    }
}
