package tn.esprit.climora_planification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.climora_planification.domain.entities.Evaluation;

public interface IEvaluationRepository  extends JpaRepository<Evaluation, Long> {
}
