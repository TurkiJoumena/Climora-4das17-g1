package com.climora.alertesnotificationsservice.web.controller;
import com.climora.alertesnotificationsservice.domain.Notification;
import com.climora.alertesnotificationsservice.service.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final INotificationService notificationService;
    @GetMapping
    public List<Notification> findByUserId(@RequestHeader("X-User-Id") String userId) {
        return notificationService.findByUserId(userId);
    }
    @PatchMapping("/{id}/lue")
    public Notification marquerLue(@PathVariable String id) {
        return notificationService.marquerLue(id);
    }
}