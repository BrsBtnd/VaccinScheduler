package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository mockUserRepository;

    @Autowired
    private UserService userService;

    @Test
    public void getUserWithInvalidId() {

        when(mockUserRepository.findById(-1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userService.findByUserId(-1));
    }

    @Test
    public void getUserWithCorrectId() {
        User user = createUser(1, "1", "fname1", "lname1", "uname1", "upasswd1");

        when(mockUserRepository.findById(1)).thenReturn(Optional.of(user));
        assertEquals(user.getUserId(), userService.findByUserId(1).getUserId());
    }

    @Test
    public void getAllUsersFromEmptyRepository() {
        List<User> userList = new ArrayList<>();
        when(mockUserRepository.findAll()).thenReturn(userList);

        assertEquals(0, userService.findAllUser().size());
    }

    @Test
    public void getAllUsersFromRepository() {
        List<User> userList = new ArrayList<>();
        User user1 = createUser(1, "1", "fname1", "lname1", "uname1", "upasswd1");
        User user2 = createUser(2, "2", "fname2", "lname2", "uname2", "upasswd2");
        userList.add(user1);
        userList.add(user2);
        when(mockUserRepository.findAll()).thenReturn(userList);

        assertEquals(user1, userService.findAllUser().get(0));
        assertEquals(user2, userService.findAllUser().get(1));
    }

    @Test
    public void deleteUserWithNotExistingId() {

        when(mockUserRepository.deleteByUserId(0)).thenReturn(0L);
        assertFalse(userService.deleteByUserId(0));
    }

    @Test
    public void updateUserWithInvalidUserId() {
        when(mockUserRepository.findById(-1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.updateUser(-1,
                createUser(-1, "123", "fname", "lname", "uname", "pswd")));
    }

    @Test
    public void updateUserWithValid() {
        User user = createUser(1, "123", "fname", "lname", "uname", "pswd");
        when(mockUserRepository.findById(1)).thenReturn(Optional.of(user));
        when(mockUserRepository.save(user)).thenReturn(user);
        assertEquals(1, userService.updateUser(1, user).getUserId());
    }

    private User createUser(int id, String cnp, String fname, String lname, String uname, String passwd) {
        User user = new User();
        user.setUserId(id);
        user.setCnp(cnp);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setUsername(uname);
        user.setPassword(passwd);

        return user;
    }

}