package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.entities.Vaccine;
import com.example.vaccinescheduler.repositories.UserScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserScheduleService {

    private UserScheduleRepository userScheduleRepository;
    private UserService userService;
    private VaccineService vaccineService;

    @Autowired
    public UserScheduleService(UserScheduleRepository userScheduleRepository,
                               UserService userService, VaccineService vaccineService) {
        this.userScheduleRepository = userScheduleRepository;
        this.userService = userService;
        this.vaccineService = vaccineService;
    }

    public UserSchedule findByUserScheduleId(int userScheduleId) {
        return this.userScheduleRepository.findById(userScheduleId)
                .orElseThrow(() -> new NoSuchElementException("UserSchedule with userScheduleId: " + userScheduleId + " not found"));
    }

    public List<UserSchedule> findAllUserScheduleByUserId(int userId) {
        return this.userScheduleRepository.findByUser(userService.findByUserId(userId))
                .orElseThrow(() -> new NoSuchElementException("UserSchedules with userId: " + userId + " not found"));
    }

    public List<UserSchedule> findAllUserScheduleByVaccineId(int vaccineId) {
        return this.userScheduleRepository.findByVaccine(vaccineService.findByVaccineId(vaccineId))
                .orElseThrow(() -> new NoSuchElementException("UserSchedules with vaccineId: " + vaccineId + " not found"));
    }

    public List<UserSchedule> findAllUserScheduleByUserIdAndByVaccineId(int userId, int vaccineId) {
        return this.userScheduleRepository.findByUserAndVaccine(userService.findByUserId(userId),
                        vaccineService.findByVaccineId(vaccineId))
                .orElseThrow(() -> new NoSuchElementException("UserSchedules with userId: " + userId + " and " +
                        "with vaccineId: " + vaccineId + " not found"));
    }

    public List<UserSchedule> findAllUserSchedule() {
        return this.userScheduleRepository.findAll();
    }

    public UserSchedule saveUserSchedule(UserSchedule userSchedule) {
        return userScheduleRepository.save(userSchedule);
    }

    public UserSchedule updateUserSchedule(int userScheduleId, UserSchedule updatedUserSchedule) {
        UserSchedule currentUserSchedule = new UserSchedule();
        currentUserSchedule.setVaccineDate(updatedUserSchedule.getVaccineDate());
        currentUserSchedule.setUser(updatedUserSchedule.getUser());
        currentUserSchedule.setCenter(updatedUserSchedule.getCenter());

        return this.userScheduleRepository.save(currentUserSchedule);
    }

    @Transactional
    public boolean deleteByUserScheduleId(int userScheduleId) {
        return userScheduleRepository.deleteByUserScheduleId(userScheduleId) == 1;
    }
}
