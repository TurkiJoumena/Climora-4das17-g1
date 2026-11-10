package com.climora.alertesnotificationsservice.service;
import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import com.climora.alertesnotificationsservice.domain.Notification;
import java.util.List;

public interface IAlerteDeclencheeService {
    AlerteDeclenchee create(AlerteDeclenchee alerte);
    AlerteDeclenchee findById(String id);
    List<AlerteDeclenchee> findAll();
    Notification ajouterNotification(String alerteId, Notification notification);
}