package com.climora.alertesnotificationsservice.service;
import com.climora.alertesnotificationsservice.domain.PreferenceNotification;
import com.climora.alertesnotificationsservice.domain.enums.CanalNotification;

public interface IPreferenceNotificationService {
    PreferenceNotification findByUserId(String userId);
    PreferenceNotification update(String userId, PreferenceNotification preference);
    boolean accepte(PreferenceNotification preference, CanalNotification canal);
}