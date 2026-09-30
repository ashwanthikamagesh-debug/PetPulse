package com.petpulse.petpulse;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByPetId(String petId);
}