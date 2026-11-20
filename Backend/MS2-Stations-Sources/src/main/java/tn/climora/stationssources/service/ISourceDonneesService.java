package tn.climora.stationssources.service;

import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.VerificationSource;
import tn.climora.stationssources.domain.enums.TypeDonnees;
import tn.climora.stationssources.web.dto.SourceRequest;
import tn.climora.stationssources.web.dto.SourceUpdateRequest;

import java.util.List;

public interface ISourceDonneesService {

    List<SourceDonnees> findAll();

    SourceDonnees findById(Long id);

    SourceDonnees findActive(TypeDonnees typeDonnees);

    SourceDonnees create(SourceRequest request);

    SourceDonnees update(Long id, SourceUpdateRequest request);

    SourceDonnees activer(Long id);

    List<VerificationSource> findVerifications(Long sourceId);
}
