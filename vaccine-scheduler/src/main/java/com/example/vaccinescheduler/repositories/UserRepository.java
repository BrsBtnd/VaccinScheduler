package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Long deleteByUserId(int ID);
    Optional<User> findByUsername(String userName);
}
