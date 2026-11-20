package tn.climora.stationssources.web.dto;

import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.Station;
import tn.climora.stationssources.domain.VerificationSource;

import java.util.List;

// conversion entite vers dto, pour ne pas exposer les entites jpa dans le json
public final class DtoMapper {

    private DtoMapper() {
    }

    public static StationResponse toResponse(Station s) {
        return new StationResponse(s.getId(), s.getNom(), s.getCodeExterne(), s.getLatitude(), s.getLongitude(),
                s.getStatut(), s.getSource() == null ? null : s.getSource().getId());
    }

    public static SourceResponse toResponse(SourceDonnees src, List<Station> stations) {
        return new SourceResponse(src.getId(), src.getNom(), src.getUrlApi(), src.getUrlTest(), src.getTypeDonnees(),
                src.getModeAcces(), src.getPriorite(), src.getStatut(),
                stations.stream().map(DtoMapper::toResponse).toList());
    }

    public static VerificationResponse toResponse(VerificationSource v) {
        return new VerificationResponse(v.getId(), v.getDateVerification(), v.getDisponible(), v.getTempsReponseMs());
    }
}
