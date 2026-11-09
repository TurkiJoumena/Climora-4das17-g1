package tn.esprit.climora_planification.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;


import tn.esprit.climora_planification.domain.enume.StatutActivite;

@Entity
@Table(name = "activites_planifiees")
@Getter
@Setter
@NoArgsConstructor
public class ActivitePlanifiee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private Long villeId;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private StatutActivite statut;

    @ManyToOne
    @JsonBackReference("TypeActivite-Activite")
    private TypeActivite typeActivite;


    @OneToMany(
            mappedBy = "activite",
            cascade = CascadeType.ALL)

    @JsonManagedReference("Activite-Evaluation")
    private Set<Evaluation> evaluations;}