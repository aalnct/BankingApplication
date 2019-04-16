package com.banking.userfront.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AmitAgarwal on 4/11/19.
 */
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @DateTimeFormat
    private Date date;

    @Column
    private String location;

    @Column
    private String description;

    @Column
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "userid")
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
