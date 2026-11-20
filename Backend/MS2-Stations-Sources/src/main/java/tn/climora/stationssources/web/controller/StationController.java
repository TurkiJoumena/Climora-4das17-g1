package tn.climora.stationssources.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.climora.stationssources.service.IStationService;
import tn.climora.stationssources.web.dto.ChangementStatutRequest;
import tn.climora.stationssources.web.dto.DtoMapper;
import tn.climora.stationssources.web.dto.StationProcheResponse;
import tn.climora.stationssources.web.dto.StationRequest;
import tn.climora.stationssources.web.dto.StationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@RequiredArgsConstructor
@Tag(name = "Stations", description = "Stations de mesure des sources")
public class StationController {

    private final IStationService stationService;

    @GetMapping("/proches")
    @Operation(summary = "Stations en service de la source d'observation active, de la plus proche a la plus eloignee (appele par MS3)")
    public List<StationProcheResponse> findProches(@RequestParam double lat,
                                                   @RequestParam double lon,
                                                   @RequestParam(defaultValue = "100") double rayon) {
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180 || rayon <= 0) {
            throw new IllegalArgumentException("Coordonnees ou rayon invalides");
        }
        return stationService.findProches(lat, lon, rayon);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une station")
    public StationResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(stationService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une station")
    public StationResponse update(@PathVariable Long id, @Valid @RequestBody StationRequest request) {
        return DtoMapper.toResponse(stationService.update(id, request));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Changer le statut d'une station")
    public StationResponse changerStatut(@PathVariable Long id, @Valid @RequestBody ChangementStatutRequest request) {
        return DtoMapper.toResponse(stationService.changerStatut(id, request.statut()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer une station")
    public void delete(@PathVariable Long id) {
        stationService.delete(id);
    }
}
