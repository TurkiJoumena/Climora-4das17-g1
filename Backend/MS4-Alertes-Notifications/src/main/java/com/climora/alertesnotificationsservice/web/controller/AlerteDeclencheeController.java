package com.climora.alertesnotificationsservice.web.controller;
import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import com.climora.alertesnotificationsservice.service.IAlerteDeclencheeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/alertes")
@RequiredArgsConstructor
public class AlerteDeclencheeController {
    private final IAlerteDeclencheeService alerteService;
    @GetMapping
    public List<AlerteDeclenchee> findAll() {
        return alerteService.findAll();
    }
    @GetMapping("/{id}")
    public AlerteDeclenchee findById(@PathVariable String id) {
        return alerteService.findById(id);
    }
}