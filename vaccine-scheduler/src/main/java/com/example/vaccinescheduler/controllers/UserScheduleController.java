package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.services.UserScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserScheduleController {

    private UserScheduleService userScheduleService;

    @Autowired
    public UserScheduleController(UserScheduleService userScheduleService) {
        this.userScheduleService = userScheduleService;
    }

    @GetMapping("/api/userSchedules")
    public ResponseEntity<?> getAllUserSchedules() {
        List<UserSchedule> userScheduleList = userScheduleService.findAllUserSchedule();
        return userScheduleList.size() != 0 ? ResponseEntity.ok().body(userScheduleList) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/api/userSchedules/{userScheduleId}")
    public ResponseEntity<?> getUserScheduleById(@PathVariable int userScheduleId) {
        try {
            return ResponseEntity.ok().body(userScheduleService.findByUserScheduleId(userScheduleId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/users/{userId}/userSchedules")
    public ResponseEntity<?> getAllUserScheduleByUserId(@PathVariable int userId) {
        try {
            return ResponseEntity.ok().body(userScheduleService.findAllUserScheduleByUserId(userId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/vaccines/{vaccineId}/userSchedules")
    public ResponseEntity<?> getAllUserScheduleByVaccineId(@PathVariable int vaccineId) {
        try {
            return ResponseEntity.ok().body(userScheduleService.findAllUserScheduleByVaccineId(vaccineId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/userSchedules")
    public ResponseEntity<?> createUserSchedule(@Valid @RequestBody UserSchedule userSchedule) {
        return ResponseEntity.ok().body(userScheduleService.saveUserSchedule(userSchedule));
    }

    @PutMapping("/api/userSchedules/{userScheduleId}")
    public ResponseEntity<?> updateUserSchedule(@PathVariable int userScheduleId, @Valid @RequestBody UserSchedule userSchedule) {
        return ResponseEntity.ok().body(userScheduleService.updateUserSchedule(userScheduleId, userSchedule));
    }

    @DeleteMapping("/api/userSchedules/{userScheduleId}")
    public ResponseEntity<?> deleteUserSchedule(@PathVariable int userScheduleId) {
        return userScheduleService.deleteByUserScheduleId(userScheduleId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
