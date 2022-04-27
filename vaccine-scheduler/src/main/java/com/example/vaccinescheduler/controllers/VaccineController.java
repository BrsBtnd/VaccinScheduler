package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.entities.Vaccine;
import com.example.vaccinescheduler.services.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllVaccine() {
        List<Vaccine> vaccineList = vaccineService.findAllVaccine();
        return vaccineList.size() != 0 ? ResponseEntity.ok().body(vaccineList) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{vaccineId}")
    public ResponseEntity<?> getVaccineByVaccineId(@PathVariable int vaccineId) {
        try {
            return ResponseEntity.ok().body(vaccineService.findByVaccineId(vaccineId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createVaccine(@Valid @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok().body(vaccineService.saveVaccine(vaccine));
    }

    @PutMapping("/{vaccineId}")
    public ResponseEntity<?> updateVaccineByVaccineId(@PathVariable int vaccineId, @Valid @RequestBody Vaccine vaccine) {
        return ResponseEntity.ok().body(vaccineService.updateVaccine(vaccineId, vaccine));
    }

    @DeleteMapping("/{vaccineId}")
    public ResponseEntity<?> deleteVaccineByVaccineId(@PathVariable int vaccineId) {
        return vaccineService.deleteByVaccineId(vaccineId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
