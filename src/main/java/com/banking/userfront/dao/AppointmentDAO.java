package com.banking.userfront.dao;

import com.banking.userfront.domain.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by AmitAgarwal on 4/23/19.
 */
//@Repository
public interface AppointmentDAO extends CrudRepository<Appointment, Long>{
    List<Appointment> findAll();
    //Appointment findOne(Long id);
}
