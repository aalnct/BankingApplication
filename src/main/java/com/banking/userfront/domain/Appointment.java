package com.banking.userfront.domain;

import java.util.Date;

/**
 * Created by AmitAgarwal on 4/11/19.
 */
public class Appointment {
    private Long id;
    private Date date;
    private String location;
    private String description;
    private boolean confirmed;
    private User user;

    public Appointment(Date date, String location, String description, boolean confirmed, User user) {
        this.date = date;
        this.location = location;
        this.description = description;
        this.confirmed = confirmed;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
