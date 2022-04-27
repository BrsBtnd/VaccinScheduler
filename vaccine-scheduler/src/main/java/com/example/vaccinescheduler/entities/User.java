package com.example.vaccinescheduler.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "cnp", unique = true, nullable = false)
    private String cnp;

    @Column(name = "first_name", nullable = false)
//    @Pattern(regexp = "^([^0-9]*)$")
    private String firstName;

    @Column(name = "last_name", nullable = false)
//    @Pattern(regexp = "^([^0-9]*)$")
    private String lastName;

    @Column(name = "gender")
//    @Pattern(regexp = "^([^0-9]*)$")
    private String gender;

    @Column(name = "user_password")
    private String password;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "age", nullable = false)
//    @PositiveOrZero
//    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Column(name = "user_role")
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
