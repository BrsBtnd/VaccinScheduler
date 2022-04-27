package com.example.vaccinescheduler.services;

import com.example.vaccinescheduler.entities.Vaccine;
import com.example.vaccinescheduler.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VaccineService {

    private VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public Vaccine findByVaccineId(int vaccineId) {
        return this.vaccineRepository.findById(vaccineId)
                .orElseThrow(() -> new NoSuchElementException("Vaccine with vaccineId: " + vaccineId + " not found"));
    }

    public List<Vaccine> findAllVaccine() {
        return this.vaccineRepository.findAll();
    }

    public Vaccine saveVaccine(Vaccine vaccine) {   //need password encoding and soo on, this is just for testing
        return this.vaccineRepository.save(vaccine);
    }

    public Vaccine updateVaccine(int vaccineId, Vaccine updatedVaccine) {

        Vaccine currentVaccine = findByVaccineId(vaccineId);
        currentVaccine.setVaccineType(updatedVaccine.getVaccineType());
        currentVaccine.setVaccineName(updatedVaccine.getVaccineName());
        currentVaccine.setProducer(updatedVaccine.getProducer());

        return this.vaccineRepository.save(currentVaccine);
    }

    @Transactional
    public boolean deleteByVaccineId(int vaccineId) {
        return this.vaccineRepository.deleteByVaccineId(vaccineId) == 1;
    }
}
