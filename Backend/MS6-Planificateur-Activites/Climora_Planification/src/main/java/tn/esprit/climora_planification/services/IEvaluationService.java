package tn.esprit.climora_planification.services;

import tn.esprit.climora_planification.domain.entities.Evaluation;

import java.util.List;

public interface IEvaluationService {

    Evaluation evaluateActivity(Long activityId);

    List<Evaluation> findEvaluationHistory(Long activityId);
}
