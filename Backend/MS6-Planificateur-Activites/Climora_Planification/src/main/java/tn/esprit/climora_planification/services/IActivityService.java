package tn.esprit.climora_planification.services;

import tn.esprit.climora_planification.domain.entities.ActivitePlanifiee;

import java.util.List;

public interface IActivityService {

    ActivitePlanifiee planActivity(ActivitePlanifiee activity);

    List<ActivitePlanifiee> findMyActivities(String userId);

    ActivitePlanifiee findById(Long id);

    ActivitePlanifiee updateActivity(Long id, ActivitePlanifiee activity);

    ActivitePlanifiee cancelActivity(Long id);
}
