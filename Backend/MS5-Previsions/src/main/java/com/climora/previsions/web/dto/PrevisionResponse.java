package com.climora.previsions.web.dto;

import com.climora.previsions.domain.entity.Prevision;
import com.climora.previsions.domain.entity.PrevisionJour;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public record PrevisionResponse(
    Long villeId,
    LocalDateTime dateGeneration,
    List<PrevisionJourResponse> jours) {

    public static PrevisionResponse from(Prevision prevision) {
        List<PrevisionJourResponse> jours = prevision.getJours().stream()
            .sorted(Comparator.comparing(PrevisionJour::getDate))
            .map(PrevisionJourResponse::from)
            .toList();
        return new PrevisionResponse(prevision.getVilleId(), prevision.getDateGeneration(), jours);
    }
}
