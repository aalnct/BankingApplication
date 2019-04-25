package com.banking.userfront.service;

import com.banking.userfront.domain.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by AmitAgarwal on 4/23/19.
 */
@Service
public interface AppointmentService {


    void createAppointment(Appointment appointment);
    List<Appointment> findAll();
    Optional<Appointment> findAppointment(Long id);
    void confirmAppointment(Long id);
}
