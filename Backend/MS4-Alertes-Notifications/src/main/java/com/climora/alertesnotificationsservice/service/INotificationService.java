package com.climora.alertesnotificationsservice.service;
import com.climora.alertesnotificationsservice.domain.Notification;
import java.util.List;

public interface INotificationService {
    Notification findById(String id);
    List<Notification> findAll();
    List<Notification> findByUserId(String userId);
    Notification marquerLue(String id);
}