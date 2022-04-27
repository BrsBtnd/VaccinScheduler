package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void deleteUserWithNegativeId() {
        assertEquals(0L, userRepository.deleteByUserId(-1));
    }

    @Test
    public void saveFindDeleteUser() {
        User savedUser = userRepository.save(createUser());
        User foundUser = userRepository.findById(savedUser.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User with userId: " + savedUser.getUserId() + " not found"));

        assertEquals(1L, userRepository.deleteByUserId(foundUser.getUserId()));
    }

    @Test
    public void findByUsernameTest() {
        User savedUser = userRepository.save(createUser());
        User foundedUser = userRepository.findByUsername(savedUser.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User with username: " + savedUser.getUsername() +" not found"));

        assertEquals("uname", foundedUser.getUsername());
        userRepository.deleteByUserId(foundedUser.getUserId());
    }

    private User createUser() {
        User user = new User();
        user.setUsername("uname");
        user.setCnp("123");
        user.setFirstName("fname");
        user.setLastName("lname");
        user.setPassword("pwd");
        user.setGender("male");
        user.setAge(19);
        return user;
    }
}