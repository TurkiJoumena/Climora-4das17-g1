package tn.esprit.climora_planification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.climora_planification.domain.entities.TypeActivite;

public interface ITypeActiviteRepository extends JpaRepository<TypeActivite, Long> {
}
