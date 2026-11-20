package tn.climora.stationssources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.enums.StatutSource;
import tn.climora.stationssources.domain.enums.TypeDonnees;

import java.util.List;
import java.util.Optional;

public interface ISourceDonneesRepository extends JpaRepository<SourceDonnees, Long> {

    Optional<SourceDonnees> findByTypeDonneesAndStatut(TypeDonnees typeDonnees, StatutSource statut);

    List<SourceDonnees> findByTypeDonneesAndStatutOrderByPrioriteAsc(TypeDonnees typeDonnees, StatutSource statut);

    boolean existsByNomIgnoreCase(String nom);
}
