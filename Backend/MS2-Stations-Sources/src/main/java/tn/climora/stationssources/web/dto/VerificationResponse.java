package tn.climora.stationssources.web.dto;

import java.time.LocalDateTime;

public record VerificationResponse(
        Long id,
        LocalDateTime dateVerification,
        Boolean disponible,
        Integer tempsReponseMs) {
}
