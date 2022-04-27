package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    Long deleteByVaccineId(int vaccineId);
    Optional<Vaccine> findByVaccineName(String vaccineName);
}
