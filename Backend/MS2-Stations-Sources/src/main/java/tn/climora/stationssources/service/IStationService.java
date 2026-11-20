package tn.climora.stationssources.service;

import tn.climora.stationssources.domain.Station;
import tn.climora.stationssources.domain.enums.StatutStation;
import tn.climora.stationssources.web.dto.StationProcheResponse;
import tn.climora.stationssources.web.dto.StationRequest;

import java.util.List;

public interface IStationService {

    List<Station> findBySource(Long sourceId);

    Station findById(Long id);

    Station create(Long sourceId, StationRequest request);

    Station update(Long id, StationRequest request);

    Station changerStatut(Long id, StatutStation statut);

    void delete(Long id);

    List<StationProcheResponse> findProches(double latitude, double longitude, double rayonKm);
}
