package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {
    Long deleteByCenterId(int centerID);
    List<Center> findByCenterNameContainingIgnoreCase(String centerName);
}
