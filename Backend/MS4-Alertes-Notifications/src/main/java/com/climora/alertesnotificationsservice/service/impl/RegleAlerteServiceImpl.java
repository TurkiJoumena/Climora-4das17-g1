package com.climora.alertesnotificationsservice.service.impl;
import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import com.climora.alertesnotificationsservice.domain.RegleAlerte;
import com.climora.alertesnotificationsservice.exception.ResourceNotFoundException;
import com.climora.alertesnotificationsservice.repository.IAlerteDeclencheeRepository;
import com.climora.alertesnotificationsservice.repository.IRegleAlerteRepository;
import com.climora.alertesnotificationsservice.service.IRegleAlerteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegleAlerteServiceImpl implements IRegleAlerteService {
    private final IRegleAlerteRepository regleAlerteRepository;
    private final IAlerteDeclencheeRepository alerteDeclencheeRepository;
    @Override
    public RegleAlerte create(RegleAlerte regle) {
        if (regle.getId() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel objet RegleAlerte ne doit pas avoir d'identifiant");
        }
        if (regle.getActive() == null) {
            regle.setActive(true);
        }
        if (regle.getAlertes() == null) {
            regle.setAlertes(new ArrayList<>());
        }
        return regleAlerteRepository.save(regle);
    }
    @Override
    public RegleAlerte findById(String id) {
        return regleAlerteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RegleAlerte", id));
    }
    @Override
    public List<RegleAlerte> findAll() {
        return regleAlerteRepository.findAll();
    }
    @Override
    public List<RegleAlerte> findByUserId(String userId) {
        return regleAlerteRepository.findByUserId(userId);
    }
    @Override
    public RegleAlerte update(String id, RegleAlerte regle) {
        RegleAlerte existant = findById(id);
        existant.setVilleId(regle.getVilleId());
        existant.setTypeMesure(regle.getTypeMesure());
        existant.setSeuil(regle.getSeuil());
        if (regle.getActive() != null) {
            existant.setActive(regle.getActive());
        }
        return regleAlerteRepository.save(existant);
    }
    @Override
    public void deleteById(String id) {
        if (!regleAlerteRepository.existsById(id)) {
            throw new ResourceNotFoundException("RegleAlerte", id);
        }
        regleAlerteRepository.deleteById(id);
    }
    @Override
    public boolean estDeclenchee(RegleAlerte regle, Double valeur) {
        return Boolean.TRUE.equals(regle.getActive())
                && valeur != null
                && valeur > regle.getSeuil();
    }
    @Override
    public AlerteDeclenchee ajouterAlerte(String regleId, AlerteDeclenchee alerte) {
        RegleAlerte regle = findById(regleId);
        alerte.setRegle(regle);
        if (alerte.getDateDeclenchement() == null) {
            alerte.setDateDeclenchement(LocalDateTime.now());
        }
        if (alerte.getNotifications() == null) {
            alerte.setNotifications(new ArrayList<>());
        }
        AlerteDeclenchee enregistree = alerteDeclencheeRepository.save(alerte);

        if (regle.getAlertes() == null) {
            regle.setAlertes(new ArrayList<>());
        }
        regle.getAlertes().add(enregistree);
        regleAlerteRepository.save(regle);
        return enregistree;
    }
}