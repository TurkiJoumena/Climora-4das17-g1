package tn.climora.stationssources.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// resultat d'un test de la supervision sur une source
@Entity
@Table(name = "verification_source")
@Getter
@Setter
@NoArgsConstructor
public class VerificationSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateVerification;

    private Boolean disponible;

    private Integer tempsReponseMs;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private SourceDonnees source;
}
