package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.config.WebSecurityConfig;
import com.example.vaccinescheduler.entities.User;
import com.example.vaccinescheduler.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(WebSecurityConfig.class)
class UserControllerTest {
    @MockBean
    public UserService userService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private User user1 = createUserWithDetails(1, "uname1", "123",
            "fname1", "lname1", "male", 18);
    private User user2 = createUserWithDetails(2, "uname2", "1234",
            "fname2", "lname3", "female", 19);
    private User user3 = createUserWithDetails(3, "uname3", "1235",
            "fname3", "lname3", "male", 20);
    private List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

    @Autowired
    public UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

//    @BeforeEach
//    public void init() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("http://localhost:9000/login")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("username", "username")
//                        .param("password", "1"))
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void loginTest() throws Exception{
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:9000/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "username")
                        .param("password", "1"))
                .andDo(MockMvcResultHandlers.print());

        String resultString = result.andReturn().getResponse().getContentAsString();
        System.out.println("result" + resultString);
    }

    @Test
    public void getAllUser_fromEmptyRepository() throws Exception {
        when(userService.findAllUser()).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllUserRecords_success() throws Exception {
        when(userService.findAllUser()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getUserRecordWithInvalidUserId() throws Exception {
        when(userService.findByUserId(0)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserRecordByValidUserId() throws Exception {
        when(userService.findByUserId(1)).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postUserRecord() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserRecordWithNotExitingUserId() throws Exception {
        when(userService.deleteByUserId(0)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/users/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserWithCorrectUserId() throws Exception {
        when(userService.deleteByUserId(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private User createUserWithDetails(int id, String username, String cnp,
                                       String fname, String lname, String gender, int age) {
        User user = new User();
        user.setUserId(id);
        user.setUsername(username);
        user.setCnp(cnp);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setGender(gender);
        user.setAge(age);

        return user;
    }
}