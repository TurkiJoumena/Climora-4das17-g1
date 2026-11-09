package tn.esprit.climora_planification.domain.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import tn.esprit.climora_planification.domain.enume.Verdict;

@Entity
@Table(name = "evaluations")
@Getter
@Setter
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    @Enumerated(EnumType.STRING)
    private Verdict verdict;

    private LocalDateTime dateEvaluation;

    @ManyToOne
    @JsonBackReference("Activite-Evaluation")
    private ActivitePlanifiee activite;
}