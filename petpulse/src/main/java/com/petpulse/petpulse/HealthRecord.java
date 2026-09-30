package com.petpulse.petpulse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;
    private String treatment;
    private String vetNotes;
    private LocalDate visitDate;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonIgnore
    private Dog pet;

    public HealthRecord() {}

    public HealthRecord(String diagnosis, String treatment, String vetNotes, LocalDate visitDate, Dog pet) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.vetNotes = vetNotes;
        this.visitDate = visitDate;
        this.pet = pet;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getVetNotes() { return vetNotes; }
    public void setVetNotes(String vetNotes) { this.vetNotes = vetNotes; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public Dog getPet() { return pet; }
    public void setPet(Dog pet) { this.pet = pet; }
}