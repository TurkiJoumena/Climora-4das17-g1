package com.climora.alertesnotificationsservice.repository;

import com.climora.alertesnotificationsservice.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface INotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByUserIdOrderByDateEnvoiDesc(String userId);

    Optional<Notification> findByIdAndUserId(String id, String userId);
}