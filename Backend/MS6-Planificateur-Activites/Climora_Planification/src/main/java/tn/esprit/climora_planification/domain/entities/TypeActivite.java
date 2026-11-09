package tn.esprit.climora_planification.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import java.util.Set;

@Entity
@Table(name = "types_activites")
@Getter
@Setter
@NoArgsConstructor
public class TypeActivite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    private Double tempMin;

    private Double tempMax;

    private Integer probabilitePluieMax;

    @OneToMany(mappedBy = "typeActivite", cascade = CascadeType.ALL)
    @JsonManagedReference("TypeActivite-Activite")
    private Set<ActivitePlanifiee> activites;
}