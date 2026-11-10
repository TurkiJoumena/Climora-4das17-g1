package com.climora.previsions.service.impl;

import com.climora.previsions.domain.entity.Prevision;
import com.climora.previsions.domain.entity.PrevisionJour;
import com.climora.previsions.exception.ResourceNotFoundException;
import com.climora.previsions.repository.PrevisionRepository;
import com.climora.previsions.service.IPrevisionService;
import com.climora.previsions.web.dto.PrevisionJourResponse;
import com.climora.previsions.web.dto.PrevisionResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PrevisionServiceImpl implements IPrevisionService {

    private final PrevisionRepository previsionRepository;

    public PrevisionServiceImpl(PrevisionRepository previsionRepository) {
        this.previsionRepository = previsionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PrevisionResponse getPrevisionsVille(Long villeId) {
        return PrevisionResponse.from(trouverPrevision(villeId));
    }

    @Override
    @Transactional(readOnly = true)
    public PrevisionJourResponse getJour(Long villeId, LocalDate date) {
        PrevisionJour jour = trouverPrevision(villeId).trouverJour(date);
        if (jour == null) {
            throw new ResourceNotFoundException("Aucune prévision pour le " + date);
        }
        return PrevisionJourResponse.from(jour);
    }

    @Override
    @Transactional
    public void supprimer(Long id) {
        if (!previsionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prévision " + id + " introuvable");
        }
        previsionRepository.deleteById(id);
    }

    private Prevision trouverPrevision(Long villeId) {
        return previsionRepository.findByVilleId(villeId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Aucune prévision pour la ville " + villeId));
    }
}
