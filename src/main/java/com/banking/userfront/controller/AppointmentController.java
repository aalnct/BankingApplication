package com.banking.userfront.controller;

import com.banking.userfront.domain.Appointment;
import com.banking.userfront.domain.User;
import com.banking.userfront.service.AppointmentService;
import com.banking.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AmitAgarwal on 4/23/19.
 */
@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAppointment(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment",appointment);
        model.addAttribute("dateString");
        return "appointment";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
                                        @ModelAttribute("dateString") String date, Model model, Principal principal) throws ParseException {

        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date1 = simpleDateFormat.parse(date);
        appointment.setDate(date1);

        User user = userService.findByUserName(principal.getName());
        appointment.setUser(user);

        appointmentService.createAppointment(appointment);
        return "redirect:/userFront";

    }
}
