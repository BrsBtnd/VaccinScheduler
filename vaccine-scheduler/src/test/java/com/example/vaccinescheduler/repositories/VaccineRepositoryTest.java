package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.Vaccine;
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
class VaccineRepositoryTest {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Test
    public void deleteVaccineWithNegativeId() {
        assertEquals(0L, vaccineRepository.deleteByVaccineId(-1));
    }

    @Test
    public void saveFindDeleteVaccine() {
        Vaccine savedVaccine = vaccineRepository.save(createVaccine());
        Vaccine foundVaccine = vaccineRepository.findById(savedVaccine.getVaccineId())
                .orElseThrow(() -> new NoSuchElementException("Vaccine with vaccineId: " + savedVaccine.getVaccineId() + " not found"));

        assertEquals(1L, vaccineRepository.deleteByVaccineId(foundVaccine.getVaccineId()));
    }

    @Test
    public void findByVaccineNameTest() {
        Vaccine savedVaccine = vaccineRepository.save(createVaccine());
        Vaccine foundedVaccine = vaccineRepository.findByVaccineName(savedVaccine.getVaccineName())
                .orElseThrow(() -> new NoSuchElementException("Vaccine with vaccine name: " + savedVaccine.getVaccineName() + " not found"));

        assertEquals("uname", foundedVaccine.getVaccineName());
        vaccineRepository.deleteByVaccineId(foundedVaccine.getVaccineId());
    }

    private Vaccine createVaccine() {
        Vaccine vaccine = new Vaccine();
        vaccine.setVaccineName("uname");
        vaccine.setVaccineType("mRNA");
        vaccine.setOrigin("USA");
        vaccine.setProducer("Pfizer");

        return vaccine;
    }
}