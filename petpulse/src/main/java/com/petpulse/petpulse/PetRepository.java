package com.petpulse.petpulse;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Dog, String> {
    List<Dog> findByUserId(Long userId);
}