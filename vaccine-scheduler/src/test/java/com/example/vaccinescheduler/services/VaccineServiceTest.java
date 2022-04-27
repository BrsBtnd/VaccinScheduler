package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.Vaccine;
import com.example.vaccinescheduler.repositories.VaccineRepository;
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
class VaccineServiceTest {

    @MockBean
    private VaccineRepository vaccineRepository;

    @Autowired
    private VaccineService vaccineService;

    @Test
    public void getVaccineWithInvalidId() {

        when(vaccineRepository.getById(-1)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> vaccineService.findByVaccineId(-1));
    }

    @Test
    public void getVaccineWithCorrectId() {
        Vaccine vaccine = createVaccine(1, "v1", "type1", "producer1", "origin1");

        when(vaccineRepository.findById(1)).thenReturn(Optional.of(vaccine));
        assertEquals(vaccine.getVaccineId(), vaccineService.findByVaccineId(1).getVaccineId());
    }

    @Test
    public void getAllVaccinesFromEmptyRepository() {
        List<Vaccine> vaccineList = new ArrayList<>();
        when(vaccineRepository.findAll()).thenReturn(vaccineList);

        assertEquals(0, vaccineService.findAllVaccine().size());
    }

    @Test
    public void getAllVaccinesFromRepository() {
        List<Vaccine> vaccineList = new ArrayList<>();
        Vaccine center1 = createVaccine(1, "v1", "type1", "producer1", "origin1");
        Vaccine center2 = createVaccine(2, "v2", "type2", "producer2", "origin2");
        vaccineList.add(center1);
        vaccineList.add(center2);
        when(vaccineRepository.findAll()).thenReturn(vaccineList);

        assertEquals(center1, vaccineService.findAllVaccine().get(0));
        assertEquals(center2, vaccineService.findAllVaccine().get(1));
    }

    @Test
    public void deleteVaccineWithNotExistingId() {

        when(vaccineRepository.deleteByVaccineId(0)).thenReturn(0L);
        assertFalse(vaccineService.deleteByVaccineId(0));
    }

    private Vaccine createVaccine(int id, String name, String type, String producer, String origin) {
        Vaccine vaccine = new Vaccine();

        vaccine.setVaccineId(id);
        vaccine.setVaccineName(name);
        vaccine.setVaccineType(type);
        vaccine.setProducer(producer);
        vaccine.setOrigin(origin);

        return vaccine;
    }
}