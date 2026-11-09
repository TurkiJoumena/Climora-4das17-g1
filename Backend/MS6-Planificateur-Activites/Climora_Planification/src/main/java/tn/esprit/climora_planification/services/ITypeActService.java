package tn.esprit.climora_planification.services;

import tn.esprit.climora_planification.domain.entities.TypeActivite;

import java.util.List;

public interface ITypeActService {
    TypeActivite create(TypeActivite activityType);

    TypeActivite findById(Long id);

    List<TypeActivite> findAll();

    TypeActivite update(Long id, TypeActivite activityType);

    void deleteById(Long id);
}
