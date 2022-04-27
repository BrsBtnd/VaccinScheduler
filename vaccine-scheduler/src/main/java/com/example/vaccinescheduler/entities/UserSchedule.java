package com.example.vaccinescheduler.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_schedules")
public class UserSchedule {

    @Id
    @Column(name = "user_schedule_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userScheduleId;

    @Column(name = "vaccine_date", nullable = false)
    private LocalDateTime vaccineDate;

    @OneToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public int getUserScheduleId() {
        return userScheduleId;
    }

    public void setUserScheduleId(int userScheduleId) {
        this.userScheduleId = userScheduleId;
    }

    public LocalDateTime getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(LocalDateTime vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
