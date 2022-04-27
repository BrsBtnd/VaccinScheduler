package com.example.vaccinescheduler.repositories;

import com.example.vaccinescheduler.entities.Center;
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
class CenterRepositoryTest {

    @Autowired
    private CenterRepository centerRepository;

    @Test
    public void deleteCenterWithNegativeId() {
        assertEquals(0L, centerRepository.deleteByCenterId(-1));
    }

    @Test
    public void saveFindDeleteCenter() {
        Center savedCenter = centerRepository.save(createCenter());
        Center foundCenter = centerRepository.findById(savedCenter.getCenterId())
                .orElseThrow(() -> new NoSuchElementException("Center with centerId: " + savedCenter.getCenterId() + " not found"));

        assertEquals(1L, centerRepository.deleteByCenterId(foundCenter.getCenterId()));
    }

    private Center createCenter() {
        Center center = new Center();
        center.setCenterName("cname");
        center.setCountry("country");
        center.setRegion("region");
        center.setCity("city");
        center.setStreet("street");
        center.setStreetNumber(23);
        return center;
    }
}