package tn.climora.stationssources.web.dto;

public record StationProcheResponse(
        Long id,
        String nom,
        String codeExterne,
        Double latitude,
        Double longitude,
        Long sourceId,
        Double distanceKm) {
}
