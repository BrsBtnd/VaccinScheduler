package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.repositories.CenterRepository;
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
class CenterServiceTest {

    @MockBean
    private CenterRepository mockCenterRepository;

    @Autowired
    private CenterService centerService;

    @Test
    public void getCenterWithInvalidId() {

        when(mockCenterRepository.getById(-1)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> centerService.findByCenterId(-1));
    }

    @Test
    public void getCenterWithCorrectId() {
        Center center = createCenter(1, "c1", "country1", "region1", "city1", 1);

        when(mockCenterRepository.findById(1)).thenReturn(Optional.of(center));
        assertEquals(center.getCenterId(), centerService.findByCenterId(1).getCenterId());
    }

    @Test
    public void getAllCentersFromEmptyRepository() {
        List<Center> centerList = new ArrayList<>();
        when(mockCenterRepository.findAll()).thenReturn(centerList);

        assertEquals(0, centerService.findAllCenter().size());
    }

    @Test
    public void getAllCentersFromRepository() {
        List<Center> centerList = new ArrayList<>();
        Center center1 = createCenter(1, "c1", "country1", "region1", "city1", 1);
        Center center2 = createCenter(2, "c2", "country2", "region2", "city2", 2);
        centerList.add(center1);
        centerList.add(center2);
        when(mockCenterRepository.findAll()).thenReturn(centerList);

        assertEquals(center1, centerService.findAllCenter().get(0));
        assertEquals(center2, centerService.findAllCenter().get(1));
    }

    @Test
    public void deleteCenterWithNotExistingId() {

        when(mockCenterRepository.deleteByCenterId(0)).thenReturn(0L);
        assertFalse(centerService.deleteByCenterId(0));
    }

    private Center createCenter(int id, String name, String country, String region, String city, int number) {
        Center center = new Center();

        center.setCenterId(id);
        center.setCenterName(name);
        center.setCountry(country);
        center.setRegion(region);
        center.setCity(city);
        center.setStreetNumber(number);

        return center;
    }
}