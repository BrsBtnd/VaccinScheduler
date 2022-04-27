package com.example.vaccinescheduler.controllers;

import com.example.vaccinescheduler.entities.Center;
import com.example.vaccinescheduler.services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    private CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCenters() {
        List<Center> centerList = centerService.findAllCenter();
        return centerList.size() != 0 ? ResponseEntity.ok().body(centerList) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{centerId}")
    public ResponseEntity<?> getCenterByCenterId(@PathVariable int centerId) {
        try {
            return ResponseEntity.ok().body(centerService.findByCenterId(centerId));
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{centerName}")
    public ResponseEntity<?> getCenterByCenterName(@PathVariable String centerName) {
        return ResponseEntity.ok().body(centerService.findAllCenterByName(centerName));
    }

    @PostMapping("")
    public ResponseEntity<?> createCenter(@Valid @RequestBody Center center) {
        return ResponseEntity.ok().body(centerService.saveCenter(center));
    }

    @PutMapping("{centerId}")
    public ResponseEntity<?> updateCenterByCenterId(@PathVariable int centerId, @Valid @RequestBody Center center) {
        return ResponseEntity.ok().body(centerService.updateCenter(centerId, center));
    }

    @DeleteMapping("{centerId}")
    public ResponseEntity<?> deleteCenterByCenterId(@PathVariable int centerId) {
        return centerService.deleteByCenterId(centerId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
