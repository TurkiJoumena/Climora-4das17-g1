package com.climora.alertesnotificationsservice.web.controller;
import com.climora.alertesnotificationsservice.domain.RegleAlerte;
import com.climora.alertesnotificationsservice.service.IRegleAlerteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/regles")
@RequiredArgsConstructor
public class RegleAlerteController {
    private final IRegleAlerteService regleAlerteService;
    @GetMapping
    public List<RegleAlerte> findByUserId(@RequestHeader("X-User-Id") String userId) {
        return regleAlerteService.findByUserId(userId);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegleAlerte create(@RequestHeader("X-User-Id") String userId,
                              @RequestBody RegleAlerte regle) {
        regle.setUserId(userId);
        return regleAlerteService.create(regle);
    }
    @PutMapping("/{id}")
    public RegleAlerte update(@PathVariable String id, @RequestBody RegleAlerte regle) {
        return regleAlerteService.update(id, regle);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
        regleAlerteService.deleteById(id);
    }
}