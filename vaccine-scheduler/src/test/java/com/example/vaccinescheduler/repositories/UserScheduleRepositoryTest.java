package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.entities.Vaccine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserScheduleRepositoryTest {

    private UserScheduleRepository userScheduleRepository;
    private UserRepository userRepository;
    private CenterRepository centerRepository;
    private VaccineRepository vaccineRepository;

    @Autowired
    public UserScheduleRepositoryTest(UserScheduleRepository userScheduleRepository,
                                      UserRepository userRepository, CenterRepository centerRepository,
                                      VaccineRepository vaccineRepository) {
        this.userScheduleRepository = userScheduleRepository;
        this.userRepository = userRepository;
        this.centerRepository = centerRepository;
        this.vaccineRepository = vaccineRepository;
    }


    @Test
    public void deleteUserScheduleWithNegativeId() {
        assertEquals(0L, userScheduleRepository.deleteByUserScheduleId(-1));
    }

    @Test
    public void saveFindDeleteUserSchedule() {
        UserSchedule savedUserSchedule = userScheduleRepository.save(createUserSchedule());
        UserSchedule foundedUserSchedule = userScheduleRepository.findById(savedUserSchedule.getUserScheduleId())
                .orElseThrow(() ->
                        new NoSuchElementException("Userschedule with userscheduleId: " + savedUserSchedule.getUserScheduleId() + " not found"));

        assertEquals(1L, userScheduleRepository.deleteByUserScheduleId(foundedUserSchedule.getUserScheduleId()));
    }

    private UserSchedule createUserSchedule() {
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setVaccineDate(LocalDateTime.now());
        userSchedule.setCenter(createCenter());
        userSchedule.setVaccine(createVaccine());
        userSchedule.setUser(createUser());
        return userSchedule;
    }

    private Center createCenter() {
        return centerRepository.findById(31)
                .orElseThrow(() -> new NoSuchElementException("Center with centerId: 31 not found"));
    }

    private User createUser() {
        return userRepository.findById(1)
                .orElseThrow(() -> new NoSuchElementException("User with userId: 1 not found"));
    }

    private Vaccine createVaccine() {
        return vaccineRepository.findById(1)
                .orElseThrow(() -> new NoSuchElementException("Vaccine with vaccineId: 1 not found"));
    }
}