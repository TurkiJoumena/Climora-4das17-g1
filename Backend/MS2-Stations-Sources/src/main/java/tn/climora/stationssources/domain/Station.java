package tn.climora.stationssources.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.climora.stationssources.domain.enums.StatutStation;

@Entity
@Table(name = "station")
@Getter
@Setter
@NoArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    // identifiant de la station chez la source, par exemple le code OACI DTTA
    private String codeExterne;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    private StatutStation statut;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private SourceDonnees source;

    public void mettreEnMaintenance() {
        this.statut = StatutStation.MAINTENANCE;
    }

    public boolean estEnService() {
        return this.statut == StatutStation.EN_SERVICE;
    }
}
