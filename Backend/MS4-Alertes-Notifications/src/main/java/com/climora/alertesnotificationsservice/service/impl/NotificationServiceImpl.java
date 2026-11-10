package com.climora.alertesnotificationsservice.service.impl;
import com.climora.alertesnotificationsservice.domain.Notification;
import com.climora.alertesnotificationsservice.domain.enums.StatutNotification;
import com.climora.alertesnotificationsservice.exception.ResourceNotFoundException;
import com.climora.alertesnotificationsservice.repository.INotificationRepository;
import com.climora.alertesnotificationsservice.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {
    private final INotificationRepository notificationRepository;
    @Override
    public Notification findById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", id));
    }
    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }
    @Override
    public List<Notification> findByUserId(String userId) {
        return notificationRepository.findByUserIdOrderByDateEnvoiDesc(userId);
    }
    @Override
    public Notification marquerLue(String id) {
        Notification notification = findById(id);
        notification.setStatut(StatutNotification.LUE);
        return notificationRepository.save(notification);
    }
}