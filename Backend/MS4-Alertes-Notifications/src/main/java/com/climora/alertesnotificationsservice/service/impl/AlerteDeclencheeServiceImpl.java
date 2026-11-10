package com.climora.alertesnotificationsservice.service.impl;
import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import com.climora.alertesnotificationsservice.domain.Notification;
import com.climora.alertesnotificationsservice.exception.ResourceNotFoundException;
import com.climora.alertesnotificationsservice.repository.IAlerteDeclencheeRepository;
import com.climora.alertesnotificationsservice.repository.INotificationRepository;
import com.climora.alertesnotificationsservice.service.IAlerteDeclencheeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlerteDeclencheeServiceImpl implements IAlerteDeclencheeService {
    private final IAlerteDeclencheeRepository alerteDeclencheeRepository;
    private final INotificationRepository notificationRepository;
    @Override
    public AlerteDeclenchee create(AlerteDeclenchee alerte) {
        if (alerte.getId() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel objet AlerteDeclenchee ne doit pas avoir d'identifiant");
        }
        if (alerte.getDateDeclenchement() == null) {
            alerte.setDateDeclenchement(LocalDateTime.now());
        }
        if (alerte.getNotifications() == null) {
            alerte.setNotifications(new ArrayList<>());
        }
        return alerteDeclencheeRepository.save(alerte);
    }
    @Override
    public AlerteDeclenchee findById(String id) {
        return alerteDeclencheeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlerteDeclenchee", id));
    }
    @Override
    public List<AlerteDeclenchee> findAll() {
        return alerteDeclencheeRepository.findAll();
    }
    @Override
    public Notification ajouterNotification(String alerteId, Notification notification) {
        AlerteDeclenchee alerte = findById(alerteId);
        notification.setAlerte(alerte);
        if (notification.getDateEnvoi() == null) {
            notification.setDateEnvoi(LocalDateTime.now());
        }
        Notification enregistree = notificationRepository.save(notification);

        if (alerte.getNotifications() == null) {
            alerte.setNotifications(new ArrayList<>());
        }
        alerte.getNotifications().add(enregistree);
        alerteDeclencheeRepository.save(alerte);
        return enregistree;
    }
}