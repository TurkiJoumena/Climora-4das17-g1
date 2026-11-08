
package tn.climora.ms1localisation.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.climora.ms1localisation.dto.FavoriteRequest;
import tn.climora.ms1localisation.entity.Favorite;
import tn.climora.ms1localisation.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // POST /api/favoris
    @PostMapping
    public ResponseEntity<Favorite> addFavorite(
            @Valid @RequestBody FavoriteRequest request) {

        Favorite favorite = favoriteService.addFavorite(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(favorite);
    }

    // GET /api/favoris?userId=user1
    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites(
            @RequestParam String userId) {

        return ResponseEntity.ok(
                favoriteService.getFavoritesByUser(userId)
        );
    }

    // DELETE /api/favoris/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(
            @PathVariable Long id) {

        favoriteService.deleteFavorite(id);

        return ResponseEntity.noContent().build();
    }
}
