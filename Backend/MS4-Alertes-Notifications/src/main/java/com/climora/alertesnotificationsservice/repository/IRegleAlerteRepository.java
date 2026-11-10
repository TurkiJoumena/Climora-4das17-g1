package com.climora.alertesnotificationsservice.repository;

import com.climora.alertesnotificationsservice.domain.RegleAlerte;
import com.climora.alertesnotificationsservice.domain.enums.TypeMesure;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IRegleAlerteRepository extends MongoRepository<RegleAlerte, String> {

    List<RegleAlerte> findByUserId(String userId);

    Optional<RegleAlerte> findByIdAndUserId(String id, String userId);

    List<RegleAlerte> findByVilleIdAndTypeMesureAndActiveTrue(Long villeId, TypeMesure typeMesure);
}