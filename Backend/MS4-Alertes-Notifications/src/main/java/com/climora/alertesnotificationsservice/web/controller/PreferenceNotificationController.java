package com.climora.alertesnotificationsservice.web.controller;
import com.climora.alertesnotificationsservice.domain.PreferenceNotification;
import com.climora.alertesnotificationsservice.service.IPreferenceNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
@RequiredArgsConstructor
public class PreferenceNotificationController {
    private final IPreferenceNotificationService preferenceService;
    @GetMapping
    public PreferenceNotification findByUserId(@RequestHeader("X-User-Id") String userId) {
        return preferenceService.findByUserId(userId);
    }
    @PutMapping
    public PreferenceNotification update(@RequestHeader("X-User-Id") String userId,
                                         @RequestBody PreferenceNotification preference) {
        return preferenceService.update(userId, preference);
    }
}