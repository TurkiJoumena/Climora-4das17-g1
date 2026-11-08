
package tn.climora.ms1localisation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FavoriteRequest(

        @NotBlank(message = "User ID obligatoire")
        String userId,

        @NotNull(message = "City ID obligatoire")
        Long cityId

) {}
