package tn.climora.stationssources.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.climora.stationssources.domain.enums.ModeAcces;
import tn.climora.stationssources.domain.enums.StatutSource;
import tn.climora.stationssources.domain.enums.TypeDonnees;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "source_donnees")
@Getter
@Setter
@NoArgsConstructor
public class SourceDonnees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String urlApi;

    // url complete appelee par la supervision, doit repondre 200
    private String urlTest;

    @Enumerated(EnumType.STRING)
    private TypeDonnees typeDonnees;

    @Enumerated(EnumType.STRING)
    private ModeAcces modeAcces;

    // 1 = la plus prioritaire pour la bascule automatique
    private Integer priorite;

    @Enumerated(EnumType.STRING)
    private StatutSource statut;

    @OneToMany(mappedBy = "source")
    private List<Station> stations = new ArrayList<>();

    @OneToMany(mappedBy = "source")
    private List<VerificationSource> verifications = new ArrayList<>();

    public void ajouterStation(Station station) {
        stations.add(station);
        station.setSource(this);
    }

    public void ajouterVerification(VerificationSource verification) {
        verifications.add(verification);
        verification.setSource(this);
    }

    public void activer() {
        this.statut = StatutSource.ACTIVE;
    }

    public void marquerDegradee() {
        this.statut = StatutSource.DEGRADEE;
    }

    public boolean estActive() {
        return this.statut == StatutSource.ACTIVE;
    }
}
