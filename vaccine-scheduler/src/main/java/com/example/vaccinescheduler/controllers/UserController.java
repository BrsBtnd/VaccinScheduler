package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.services.UserScheduleService;
import com.example.vaccinescheduler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private UserScheduleService userScheduleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserScheduleService userScheduleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userScheduleService = userScheduleService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userService.findAllUser();
        return userList.size() != 0 ? ResponseEntity.ok().body(userList) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable int userId) {
        try {
            return ResponseEntity.ok().body(userService.findByUserId(userId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username")
    public ResponseEntity<?> getUserByUsername(@RequestParam(value = "username") String username) {
        try {
            return ResponseEntity.ok().body(userService.findByUsername(username));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @Valid @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserByUserId(@PathVariable int userId) {
        List<UserSchedule> schedules = userScheduleService.findAllUserScheduleByUserId(userId);
//        schedules.forEach(schedule -> userScheduleService.deleteByUserScheduleId(schedule.getUserScheduleId()));
        schedules.stream()
                .map(schedule -> userScheduleService.deleteByUserScheduleId(schedule.getUserScheduleId()))
                .collect(Collectors.toList());

        return userService.deleteByUserId(userId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
