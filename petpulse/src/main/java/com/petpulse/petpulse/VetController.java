package com.petpulse.petpulse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vet")
@CrossOrigin
public class VetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @GetMapping("/pet/{petId}")
    public ResponseEntity<?> getPetById(@PathVariable String petId) {
        Optional<Dog> pet = petRepository.findById(petId);
        if (pet.isPresent()) {
            return ResponseEntity.ok(pet.get());
        }
        return ResponseEntity.status(404).body("Pet ID not found.");
    }

    @GetMapping("/records/{petId}")
    public List<HealthRecord> getRecords(@PathVariable String petId) {
        return healthRecordRepository.findByPetId(petId);
    }

    @PostMapping("/records/{petId}")
    public ResponseEntity<?> addRecord(@PathVariable String petId, @RequestBody HealthRecord record) {
        Optional<Dog> petOptional = petRepository.findById(petId);
        if (petOptional.isPresent()) {
            record.setPet(petOptional.get());
            record.setVisitDate(LocalDate.now());
            HealthRecord savedRecord = healthRecordRepository.save(record);
            return ResponseEntity.ok(savedRecord);
        }
        return ResponseEntity.badRequest().body("Pet not found.");
    }
}