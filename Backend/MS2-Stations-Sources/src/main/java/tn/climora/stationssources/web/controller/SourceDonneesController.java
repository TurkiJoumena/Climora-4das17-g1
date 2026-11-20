package tn.climora.stationssources.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.climora.stationssources.domain.SourceDonnees;
import tn.climora.stationssources.domain.enums.TypeDonnees;
import tn.climora.stationssources.service.ISourceDonneesService;
import tn.climora.stationssources.service.IStationService;
import tn.climora.stationssources.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
@RequiredArgsConstructor
@Tag(name = "Sources", description = "Catalogue des sources de donnees meteo")
public class SourceDonneesController {

    private final ISourceDonneesService sourceService;
    private final IStationService stationService;

    @GetMapping
    @Operation(summary = "Lister les sources")
    public List<SourceResponse> findAll() {
        return sourceService.findAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("/active")
    @Operation(summary = "Source active d'un type, avec ses stations (appele par MS3 et MS5)")
    public SourceResponse findActive(@RequestParam TypeDonnees type) {
        return toResponse(sourceService.findActive(type));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une source")
    public SourceResponse findById(@PathVariable Long id) {
        return toResponse(sourceService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ajouter une source, creee avec le statut DISPONIBLE")
    public SourceResponse create(@Valid @RequestBody SourceRequest request) {
        return toResponse(sourceService.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une source (nom, urls, priorite, statut hors ACTIVE)")
    public SourceResponse update(@PathVariable Long id, @Valid @RequestBody SourceUpdateRequest request) {
        return toResponse(sourceService.update(id, request));
    }

    @PatchMapping("/{id}/activation")
    @Operation(summary = "Activer une source, l'ancienne source active du meme type devient DISPONIBLE")
    public SourceResponse activer(@PathVariable Long id) {
        return toResponse(sourceService.activer(id));
    }

    @GetMapping("/{id}/stations")
    @Operation(summary = "Lister les stations d'une source")
    public List<StationResponse> findStations(@PathVariable Long id) {
        return stationService.findBySource(id).stream().map(DtoMapper::toResponse).toList();
    }

    @PostMapping("/{id}/stations")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Ajouter une station a une source")
    public StationResponse createStation(@PathVariable Long id, @Valid @RequestBody StationRequest request) {
        return DtoMapper.toResponse(stationService.create(id, request));
    }

    @GetMapping("/{id}/verifications")
    @Operation(summary = "Historique des verifications d'une source (50 dernieres)")
    public List<VerificationResponse> findVerifications(@PathVariable Long id) {
        return sourceService.findVerifications(id).stream().map(DtoMapper::toResponse).toList();
    }

    private SourceResponse toResponse(SourceDonnees source) {
        return DtoMapper.toResponse(source, stationService.findBySource(source.getId()));
    }
}
