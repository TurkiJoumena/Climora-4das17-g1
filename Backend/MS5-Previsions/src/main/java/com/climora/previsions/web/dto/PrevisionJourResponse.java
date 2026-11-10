package com.climora.previsions.web.dto;

import com.climora.previsions.domain.entity.PrevisionJour;
import com.climora.previsions.domain.enums.ConditionPrevue;

import java.time.LocalDate;

public record PrevisionJourResponse(
    LocalDate date,
    Double tempMin,
    Double tempMax,
    Integer probabilitePluie,
    ConditionPrevue condition) {

    public static PrevisionJourResponse from(PrevisionJour jour) {
        return new PrevisionJourResponse(
            jour.getDate(),
            jour.getTempMin(),
            jour.getTempMax(),
            jour.getProbabilitePluie(),
            jour.getCondition());
    }
}
