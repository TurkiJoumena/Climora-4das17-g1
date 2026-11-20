package tn.climora.stationssources.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tn.climora.stationssources.domain.enums.ModeAcces;
import tn.climora.stationssources.domain.enums.TypeDonnees;

// corps recu pour creer une source
public record SourceRequest(
        @NotBlank String nom,
        @NotBlank String urlApi,
        @NotBlank String urlTest,
        @NotNull TypeDonnees typeDonnees,
        @NotNull ModeAcces modeAcces,
        @NotNull @Min(1) Integer priorite) {
}
