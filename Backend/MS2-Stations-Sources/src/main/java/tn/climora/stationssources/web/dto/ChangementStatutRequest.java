package tn.climora.stationssources.web.dto;

import jakarta.validation.constraints.NotNull;
import tn.climora.stationssources.domain.enums.StatutStation;

public record ChangementStatutRequest(@NotNull StatutStation statut) {
}
