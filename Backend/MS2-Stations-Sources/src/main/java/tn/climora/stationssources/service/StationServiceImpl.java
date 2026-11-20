package tn.climora.stationssources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.Station;
import tn.climora.stationssources.domain.enums.StatutStation;
import tn.climora.stationssources.domain.enums.TypeDonnees;
import tn.climora.stationssources.exception.ResourceNotFoundException;
import tn.climora.stationssources.repository.IStationRepository;
import tn.climora.stationssources.web.dto.StationProcheResponse;
import tn.climora.stationssources.web.dto.StationRequest;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationServiceImpl implements IStationService {

    private static final double RAYON_TERRE_KM = 6371.0;

    private final IStationRepository stationRepository;
    private final ISourceDonneesService sourceService;

    @Override
    public List<Station> findBySource(Long sourceId) {
        sourceService.findById(sourceId);
        return stationRepository.findBySourceId(sourceId);
    }

    @Override
    public Station findById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Station", id));
    }

    @Override
    public Station create(Long sourceId, StationRequest request) {
        SourceDonnees source = sourceService.findById(sourceId);
        Station station = new Station();
        copier(request, station);
        station.setStatut(StatutStation.EN_SERVICE);
        source.ajouterStation(station);
        return stationRepository.save(station);
    }

    @Override
    public Station update(Long id, StationRequest request) {
        Station station = findById(id);
        copier(request, station);
        return stationRepository.save(station);
    }

    @Override
    public Station changerStatut(Long id, StatutStation statut) {
        Station station = findById(id);
        station.setStatut(statut);
        return stationRepository.save(station);
    }

    @Override
    public void delete(Long id) {
        Station station = findById(id);
        stationRepository.delete(station);
    }

    @Override
    public List<StationProcheResponse> findProches(double latitude, double longitude, double rayonKm) {
        SourceDonnees active = sourceService.findActive(TypeDonnees.OBSERVATION);
        return stationRepository.findBySourceIdAndStatut(active.getId(), StatutStation.EN_SERVICE).stream()
                .map(s -> new StationProcheResponse(s.getId(), s.getNom(), s.getCodeExterne(), s.getLatitude(),
                        s.getLongitude(), active.getId(),
                        arrondir(distanceKm(latitude, longitude, s.getLatitude(), s.getLongitude()))))
                .filter(s -> s.distanceKm() <= rayonKm)
                .sorted(Comparator.comparing(StationProcheResponse::distanceKm))
                .toList();
    }

    private void copier(StationRequest request, Station station) {
        station.setNom(request.nom());
        station.setCodeExterne(request.codeExterne());
        station.setLatitude(request.latitude());
        station.setLongitude(request.longitude());
    }

    // formule de haversine : distance entre deux points sur la terre
    private double distanceKm(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return 2 * RAYON_TERRE_KM * Math.asin(Math.sqrt(a));
    }

    private double arrondir(double valeur) {
        return Math.round(valeur * 10.0) / 10.0;
    }
}
