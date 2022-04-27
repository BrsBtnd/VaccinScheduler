package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserScheduleRepository extends JpaRepository<UserSchedule, Integer> {

    Optional<List<UserSchedule>> findByUser(User user);
    Optional<List<UserSchedule>> findByVaccine(Vaccine vaccine);
    Optional<List<UserSchedule>> findByUserAndVaccine(User user, Vaccine vaccine);
    Long deleteByUserScheduleId(int userScheduleId);
}
