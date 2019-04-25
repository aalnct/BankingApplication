package com.banking.userfront.service;

import com.banking.userfront.dao.AppointmentDAO;
import com.banking.userfront.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by AmitAgarwal on 4/23/19.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentDAO.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDAO.findAll();
    }

    @Override
    public Optional<Appointment> findAppointment(Long id) {
        return appointmentDAO.findById(id);
    }

    @Override
    public void confirmAppointment(Long id) {
        Appointment appointment = new Appointment();
        appointment.setConfirmed(true);
        appointmentDAO.save(appointment);
    }
}
