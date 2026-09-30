package com.petpulse.petpulse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    // Fetch all pets OR fetch pets belonging to a specific user
    @GetMapping
    public List<Dog> getAllPets(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return petRepository.findByUserId(userId);
        }
        return petRepository.findAll();
    }

    // Add pet attached to a specific user
    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody Dog pet, @RequestParam(required = false) Long userId) {
        if (userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                pet.setUser(userOptional.get());
            }
        }
        Dog savedPet = petRepository.save(pet);
        return ResponseEntity.ok(savedPet);
    }

    // Update existing pet
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable String id, @RequestBody Dog updatedPet) {
        return petRepository.findById(id).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setAge(updatedPet.getAge());
            pet.setWeight(updatedPet.getWeight());
            pet.setBreed(updatedPet.getBreed());
            petRepository.save(pet);
            return ResponseEntity.ok(pet);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete pet
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable String id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}