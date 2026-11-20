package tn.climora.stationssources.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tn.climora.stationssources.domain.enums.StatutSource;

// corps recu pour modifier une source, le statut est facultatif
public record SourceUpdateRequest(
        @NotBlank String nom,
        @NotBlank String urlApi,
        @NotBlank String urlTest,
        @NotNull @Min(1) Integer priorite,
        StatutSource statut) {
}
