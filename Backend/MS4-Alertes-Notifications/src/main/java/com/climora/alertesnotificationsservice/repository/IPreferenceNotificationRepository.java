package com.climora.alertesnotificationsservice.repository;

import com.climora.alertesnotificationsservice.domain.PreferenceNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IPreferenceNotificationRepository extends MongoRepository<PreferenceNotification, String> {

    Optional<PreferenceNotification> findByUserId(String userId);
}