package com.climora.previsions.service;

import com.climora.previsions.web.dto.PrevisionJourResponse;
import com.climora.previsions.web.dto.PrevisionResponse;

import java.time.LocalDate;

public interface IPrevisionService {

    PrevisionResponse getPrevisionsVille(Long villeId);

    PrevisionJourResponse getJour(Long villeId, LocalDate date);

    void supprimer(Long id);
}
