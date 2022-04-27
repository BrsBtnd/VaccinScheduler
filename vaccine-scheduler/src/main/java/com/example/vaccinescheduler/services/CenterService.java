package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CenterService {

    private CenterRepository centerRepository;

    @Autowired
    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public Center findByCenterId(int centerId) {
        return this.centerRepository.findById(centerId)
                .orElseThrow(() -> new NoSuchElementException("Center with centerId: " + centerId + " not found"));
    }

    public List<Center> findAllCenter() {
        return this.centerRepository.findAll();
    }

    public Center saveCenter(Center center) {   //need password encoding and soo on, this is just for testing
        return this.centerRepository.save(center);
    }

    public List<Center> findAllCenterByName(String centerName) {
        return this.centerRepository.findByCenterNameContainingIgnoreCase(centerName);
    }

    public Center updateCenter(int centerId, Center updatedCenter) {
        Center currentCenter = findByCenterId(centerId);
        currentCenter.setCenterName(updatedCenter.getCenterName());
        currentCenter.setCountry(updatedCenter.getCountry());
        currentCenter.setRegion(updatedCenter.getRegion());
        currentCenter.setCity(updatedCenter.getCity());
        currentCenter.setStreetNumber(updatedCenter.getStreetNumber());

        return this.centerRepository.save(currentCenter);
    }

    @Transactional
    public boolean deleteByCenterId(int centerId) {
        return this.centerRepository.deleteByCenterId(centerId) == 1;
    }
}
