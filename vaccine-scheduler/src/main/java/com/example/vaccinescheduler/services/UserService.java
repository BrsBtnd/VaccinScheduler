package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.entities.UserSchedule;
import com.example.vaccinescheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserId(int userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with userId: " + userId + " not found"));
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User with username: " + username + " not found"));
    }

    public List<User> findAllUser() {
        return this.userRepository.findAll();
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User updateUser(int userId, User updatedUser) {
        User currentUser = findByUserId(userId);
        currentUser.setCnp(updatedUser.getCnp());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setUsername(updatedUser.getUsername());
        currentUser.setPassword(updatedUser.getPassword());

        return this.userRepository.save(currentUser);
    }

    @Transactional
    public boolean deleteByUserId(int userId) {
        return this.userRepository.deleteByUserId(userId) == 1;
    }
}
