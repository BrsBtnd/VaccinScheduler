package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.entities.Vaccine;
import com.example.vaccinescheduler.repositories.UserScheduleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserScheduleServiceTest {

    @MockBean
    private UserScheduleRepository mockUserScheduleRepository;
    @MockBean
    private UserService mockUserService;
    @MockBean
    private VaccineService mockVaccineService;

    @Autowired
    private UserScheduleService userScheduleService;

    @Test
    public void getUserScheduleWithInvalidId() {
        when(mockUserScheduleRepository.getById(-1)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> userScheduleService.findByUserScheduleId(-1));
    }

    @Test
    public void getAllUserScheduleWithInvalidUserId() {
        User user = createUser(-1);
        when(mockUserScheduleRepository.findByUser(user))
                .thenThrow(NoSuchElementException.class);
        when(mockUserService.findByUserId(-1))
                .thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () ->
                userScheduleService.findAllUserScheduleByUserId(-1));
    }

    @Test
    public void getAllUserScheduleWithCorrectUserId() {
        User user = createUser(1);
        UserSchedule userSchedule = createUserSchedule(1, LocalDateTime.now(), user, new Vaccine());

        when(mockUserScheduleRepository.findByUser(user))
                .thenReturn(Optional.of(List.of(userSchedule)));

        when(mockUserService.findByUserId(1)).thenReturn(user);

        assertEquals(userSchedule.getUserScheduleId(),
                userScheduleService.findAllUserScheduleByUserId(1).get(0).getUserScheduleId());

        assertEquals(userSchedule.getUser().getUserId(),
                userScheduleService.findAllUserScheduleByUserId(1).get(0)
                        .getUser().getUserId());
    }

    @Test
    public void getAllUserScheduleWithCorrectVaccineIdAndScheduleId() {
        Vaccine vaccine = createVaccine(1);
        UserSchedule userSchedule = createUserSchedule(1, LocalDateTime.now(), new User(), vaccine);

        when(mockUserScheduleRepository.findByVaccine(vaccine))
                .thenReturn(Optional.of(List.of(userSchedule)));

        when(mockVaccineService.findByVaccineId(1)).thenReturn(vaccine);

        assertEquals(userSchedule.getUserScheduleId(),
                userScheduleService.findAllUserScheduleByVaccineId(1).get(0).getUserScheduleId());

        assertEquals(userSchedule.getVaccine().getVaccineId(),
                userScheduleService.findAllUserScheduleByVaccineId(1).get(0)
                        .getVaccine().getVaccineId());
    }

    @Test
    public void getAllUserScheduleWithCorrectId() {
        UserSchedule userSchedule = createUserSchedule(1, LocalDateTime.now(), new User(), new Vaccine());

        when(mockUserScheduleRepository.findById(1)).thenReturn(Optional.of(userSchedule));
        assertEquals(userSchedule.getUserScheduleId(), userScheduleService.findByUserScheduleId(1).getUserScheduleId());
    }

    @Test
    public void getAllUserSchedulesFromEmptyRepository() {
        List<UserSchedule> userSchedulesList = new ArrayList<>();
        when(mockUserScheduleRepository.findAll()).thenReturn(userSchedulesList);

        assertEquals(0, userScheduleService.findAllUserSchedule().size());
    }

    @Test
    public void getAllUserScheduleFromRepository() {
        List<UserSchedule> userSchedulesList = new ArrayList<>();
        UserSchedule userSchedule1 = createUserSchedule(1, LocalDateTime.now(), new User(), new Vaccine());
        UserSchedule userSchedule2 = createUserSchedule(2, LocalDateTime.now(), new User(), new Vaccine());
        userSchedulesList.add(userSchedule1);
        userSchedulesList.add(userSchedule2);
        when(mockUserScheduleRepository.findAll()).thenReturn(userSchedulesList);

        assertEquals(userSchedule1, userScheduleService.findAllUserSchedule().get(0));
        assertEquals(userSchedule2, userScheduleService.findAllUserSchedule().get(1));
    }

    @Test
    public void deleteUserScheduleWithNotExistingId() {

        when(mockUserScheduleRepository.deleteByUserScheduleId(0)).thenReturn(0L);
        assertFalse(userScheduleService.deleteByUserScheduleId(0));
    }

    private User createUser(int userID) {
        User user = new User();
        user.setUserId(userID);
        user.setCnp("123");
        user.setFirstName("fname1");
        user.setLastName("lname1");
        user.setUsername("uname1");
        user.setPassword("upasswd1");

        return user;
    }

    private Vaccine createVaccine(int vaccineId) {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineId(vaccineId);
        vaccine.setVaccineName("v1");
        vaccine.setVaccineType("t1");
        vaccine.setOrigin("o1");
        vaccine.setProducer("p1");

        return vaccine;
    }

    private UserSchedule createUserSchedule(int id, LocalDateTime localDateTime, User user, Vaccine vaccine) {
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setUserScheduleId(id);
        userSchedule.setVaccineDate(localDateTime);
        Center center = new Center();
        userSchedule.setCenter(center);
        userSchedule.setUser(user);
        userSchedule.setVaccine(vaccine);

        return userSchedule;
    }
}