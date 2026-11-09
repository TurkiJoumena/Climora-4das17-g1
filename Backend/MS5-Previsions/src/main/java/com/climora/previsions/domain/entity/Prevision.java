package com.climora.previsions.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "previsions")
public class Prevision {

    private static final int NB_JOURS_MAX = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long villeId;      // identifiant dans MS1, sans clé étrangère

    @Column(nullable = false)
    private Long sourceId;     // identifiant dans MS2, sans clé étrangère

    @Column(nullable = false)
    private LocalDateTime dateGeneration;

    @OneToMany(mappedBy = "prevision", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrevisionJour> jours = new ArrayList<>();

    protected Prevision() {
    }

    public Prevision(Long villeId, Long sourceId) {
        this.villeId = villeId;
        this.sourceId = sourceId;
    }

    @PrePersist
    void avantEnregistrement() {
        if (dateGeneration == null) {
            dateGeneration = LocalDateTime.now();
        }
    }

    public void ajouterJour(PrevisionJour jour) {
        if (jours.size() >= NB_JOURS_MAX) {
            throw new IllegalStateException("Une prévision contient au maximum 7 jours");
        }
        jour.setPrevision(this);
        jours.add(jour);
    }

    public PrevisionJour trouverJour(LocalDate date) {
        return jours.stream()
            .filter(j -> j.getDate().equals(date))
            .findFirst()
            .orElse(null);
    }

    public Long getId() { return id; }
    public Long getVilleId() { return villeId; }
    public void setVilleId(Long villeId) { this.villeId = villeId; }
    public Long getSourceId() { return sourceId; }
    public void setSourceId(Long sourceId) { this.sourceId = sourceId; }
    public LocalDateTime getDateGeneration() { return dateGeneration; }
    public void setDateGeneration(LocalDateTime dateGeneration) { this.dateGeneration = dateGeneration; }
    public List<PrevisionJour> getJours() { return jours; }
}
