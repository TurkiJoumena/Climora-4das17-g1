package com.climora.alertesnotificationsservice.service;
import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import com.climora.alertesnotificationsservice.domain.RegleAlerte;
import java.util.List;

public interface IRegleAlerteService {
    RegleAlerte create(RegleAlerte regle);
    RegleAlerte findById(String id);
    List<RegleAlerte> findAll();
    List<RegleAlerte> findByUserId(String userId);
    RegleAlerte update(String id, RegleAlerte regle);
    void deleteById(String id);
    boolean estDeclenchee(RegleAlerte regle, Double valeur);
    AlerteDeclenchee ajouterAlerte(String regleId, AlerteDeclenchee alerte);
}