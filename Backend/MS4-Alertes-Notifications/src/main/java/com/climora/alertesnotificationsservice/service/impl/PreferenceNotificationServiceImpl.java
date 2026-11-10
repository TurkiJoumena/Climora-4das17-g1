package com.climora.alertesnotificationsservice.service.impl;
import com.climora.alertesnotificationsservice.domain.PreferenceNotification;
import com.climora.alertesnotificationsservice.domain.enums.CanalNotification;
import com.climora.alertesnotificationsservice.repository.IPreferenceNotificationRepository;
import com.climora.alertesnotificationsservice.service.IPreferenceNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreferenceNotificationServiceImpl implements IPreferenceNotificationService {
    private final IPreferenceNotificationRepository preferenceRepository;
    @Override
    public PreferenceNotification findByUserId(String userId) {
        // Si l'utilisateur n'a pas encore de préférences, on crée les valeurs par défaut
        return preferenceRepository.findByUserId(userId).orElseGet(() -> {
            PreferenceNotification preference = new PreferenceNotification();
            preference.setUserId(userId);
            preference.setEmailActif(false);
            return preferenceRepository.save(preference);
        });
    }
    @Override
    public PreferenceNotification update(String userId, PreferenceNotification preference) {
        PreferenceNotification existante = findByUserId(userId);
        existante.setEmail(preference.getEmail());
        existante.setEmailActif(preference.getEmailActif());
        return preferenceRepository.save(existante);
    }
    @Override
    public boolean accepte(PreferenceNotification preference, CanalNotification canal) {
        return canal == CanalNotification.APPLICATION
                || (Boolean.TRUE.equals(preference.getEmailActif()) && preference.getEmail() != null);
    }
}