package tn.climora.stationssources.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.climora.stationssources.domain.Station;
import tn.climora.stationssources.domain.enums.StatutStation;

import java.util.List;

public interface IStationRepository extends JpaRepository<Station, Long> {

    List<Station> findBySourceId(Long sourceId);

    List<Station> findBySourceIdAndStatut(Long sourceId, StatutStation statut);
}
