package com.example.vaccinescheduler.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "centers")
public class Center {

    @Id
    @Column(name = "center_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int centerId;

    @Column(name = "center_name", nullable = false)
    private String centerName;

    @Column(name = "country", nullable = false)
//    @Pattern(regexp = "^([^0-9]*)$")
    private String country;

    @Column(name = "region", nullable = false)
//    @Pattern(regexp = "^([^0-9]*)$")
    private String region;

    @Column(name = "city", nullable = false)
//    @Pattern(regexp = "^([^0-9]*)$")
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private int streetNumber;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}
