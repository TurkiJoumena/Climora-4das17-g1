package tn.climora.stationssources.web.dto;

import tn.climora.stationssources.domain.enums.StatutStation;

public record StationResponse(
        Long id,
        String nom,
        String codeExterne,
        Double latitude,
        Double longitude,
        StatutStation statut,
        Long sourceId) {
}
