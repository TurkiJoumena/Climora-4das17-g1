package tn.climora.stationssources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.VerificationSource;
import tn.climora.stationssources.domain.enums.StatutSource;
import tn.climora.stationssources.domain.enums.TypeDonnees;
import tn.climora.stationssources.exception.BusinessRuleException;
import tn.climora.stationssources.exception.ResourceNotFoundException;
import tn.climora.stationssources.repository.ISourceDonneesRepository;
import tn.climora.stationssources.repository.IVerificationSourceRepository;
import tn.climora.stationssources.web.dto.SourceRequest;
import tn.climora.stationssources.web.dto.SourceUpdateRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceDonneesServiceImpl implements ISourceDonneesService {

    private final ISourceDonneesRepository sourceRepository;
    private final IVerificationSourceRepository verificationRepository;

    @Override
    public List<SourceDonnees> findAll() {
        return sourceRepository.findAll();
    }

    @Override
    public SourceDonnees findById(Long id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Source", id));
    }

    @Override
    public SourceDonnees findActive(TypeDonnees typeDonnees) {
        return sourceRepository.findByTypeDonneesAndStatut(typeDonnees, StatutSource.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Aucune source active pour le type " + typeDonnees));
    }

    @Override
    public SourceDonnees create(SourceRequest request) {
        if (sourceRepository.existsByNomIgnoreCase(request.nom())) {
            throw new BusinessRuleException("Une source nommee " + request.nom() + " existe deja");
        }
        SourceDonnees source = new SourceDonnees();
        source.setNom(request.nom());
        source.setUrlApi(request.urlApi());
        source.setUrlTest(request.urlTest());
        source.setTypeDonnees(request.typeDonnees());
        source.setModeAcces(request.modeAcces());
        source.setPriorite(request.priorite());
        // une nouvelle source est de secours tant que l'admin ne l'active pas
        source.setStatut(StatutSource.DISPONIBLE);
        return sourceRepository.save(source);
    }

    @Override
    public SourceDonnees update(Long id, SourceUpdateRequest request) {
        SourceDonnees source = findById(id);
        source.setNom(request.nom());
        source.setUrlApi(request.urlApi());
        source.setUrlTest(request.urlTest());
        source.setPriorite(request.priorite());
        if (request.statut() != null) {
            changerStatut(source, request.statut());
        }
        return sourceRepository.save(source);
    }

    // le statut ACTIVE ne passe que par activer(), pour garder une seule source active par type
    private void changerStatut(SourceDonnees source, StatutSource nouveau) {
        if (nouveau == StatutSource.ACTIVE) {
            throw new BusinessRuleException("Utiliser PATCH /api/sources/{id}/activation pour activer une source");
        }
        if (source.estActive() && nouveau == StatutSource.DESACTIVEE) {
            throw new BusinessRuleException("Activer une autre source avant de desactiver la source active");
        }
        source.setStatut(nouveau);
    }

    @Override
    @Transactional
    public SourceDonnees activer(Long id) {
        SourceDonnees source = findById(id);
        if (source.getStatut() == StatutSource.DESACTIVEE) {
            throw new BusinessRuleException("Une source desactivee ne peut pas etre activee");
        }
        if (source.estActive()) {
            return source;
        }
        sourceRepository.findByTypeDonneesAndStatut(source.getTypeDonnees(), StatutSource.ACTIVE)
                .ifPresent(ancienne -> {
                    ancienne.setStatut(StatutSource.DISPONIBLE);
                    sourceRepository.save(ancienne);
                });
        source.activer();
        return sourceRepository.save(source);
    }

    @Override
    public List<VerificationSource> findVerifications(Long sourceId) {
        findById(sourceId);
        return verificationRepository.findTop50BySourceIdOrderByDateVerificationDesc(sourceId);
    }
}
