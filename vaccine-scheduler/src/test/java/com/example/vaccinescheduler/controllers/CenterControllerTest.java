package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.config.WebSecurityConfig;
import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.services.CenterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CenterController.class)
@Import(WebSecurityConfig.class)
class CenterControllerTest {
    @MockBean
    public CenterService centerService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Center center1 = createCenterWithDetails(1, "cname1", "country1",
            "region1", "city1", "street1", 1);
    private Center center2 = createCenterWithDetails(2, "cname2", "country2",
            "region2", "city3", "street2", 2);
    private Center center3 = createCenterWithDetails(3, "cname3", "country3",
            "region3", "city3", "street3", 3);
    private List<Center> centerList = new ArrayList<>(Arrays.asList(center1, center2, center3));

    @Autowired
    public CenterControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void getAllCenter_fromEmptyRepository() throws Exception {
        when(centerService.findAllCenter()).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/centers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllCenterRecords_success() throws Exception {
        when(centerService.findAllCenter()).thenReturn(centerList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/centers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getCenterRecordWithInvalidCenterId() throws Exception {
        when(centerService.findByCenterId(0)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/centers/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCenterRecordByValidCenterId() throws Exception {
        when(centerService.findByCenterId(1)).thenReturn(center1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/centers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.centerId", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postCenterRecord() throws Exception {
        when(centerService.saveCenter(any(Center.class))).thenReturn(center1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/centers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(center1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.centerId", is(1)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteCenterRecordWithNotExitingCenterId() throws Exception {
        when(centerService.deleteByCenterId(0)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/centers/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCenterWithCorrectCenterId() throws Exception {
        when(centerService.deleteByCenterId(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/centers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private Center createCenterWithDetails(int id, String centerName, String country,
                                           String region, String city, String street, int strNum) {
        Center center = new Center();
        center.setCenterId(id);
        center.setCenterName(centerName);
        center.setCountry(country);
        center.setRegion(region);
        center.setCity(city);
        center.setStreet(street);
        center.setStreetNumber(strNum);

        return center;
    }
}