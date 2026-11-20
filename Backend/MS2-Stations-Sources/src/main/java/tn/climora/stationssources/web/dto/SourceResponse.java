package tn.climora.stationssources.web.dto;

import tn.climora.stationssources.domain.enums.ModeAcces;
import tn.climora.stationssources.domain.enums.StatutSource;
import tn.climora.stationssources.domain.enums.TypeDonnees;

import java.util.List;

public record SourceResponse(
        Long id,
        String nom,
        String urlApi,
        String urlTest,
        TypeDonnees typeDonnees,
        ModeAcces modeAcces,
        Integer priorite,
        StatutSource statut,
        List<StationResponse> stations) {
}
