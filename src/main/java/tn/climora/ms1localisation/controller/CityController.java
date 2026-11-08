
package tn.climora.ms1localisation.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.climora.ms1localisation.entity.City;
import tn.climora.ms1localisation.service.CityService;
import tn.climora.ms1localisation.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/villes")
public class CityController {

    private final CityService cityService;
    private final FavoriteService favoriteService;

    public CityController(
            CityService cityService,
            FavoriteService favoriteService) {

        this.cityService = cityService;
        this.favoriteService = favoriteService;
    }

    // GET /api/villes?nom=Tunis
    @GetMapping
    public ResponseEntity<List<City>> searchCities(
            @RequestParam("nom") String nom) {

        return ResponseEntity.ok(
                cityService.searchCitiesByName(nom)
        );
    }

    // GET /api/villes/suivies
    @GetMapping("/suivies")
    public ResponseEntity<List<City>> getFollowedCities() {

        return ResponseEntity.ok(
                favoriteService.getFollowedCities()
        );
    }

    // GET /api/villes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(
            @PathVariable Long id) {

        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/villes
    @PostMapping
    public ResponseEntity<City> createCity(
            @Valid @RequestBody City city) {

        City createdCity = cityService.createCity(city);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCity);
    }

    // PUT /api/villes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable Long id,
            @Valid @RequestBody City city) {

        return ResponseEntity.ok(
                cityService.updateCity(id, city)
        );
    }

    // DELETE /api/villes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(
            @PathVariable Long id) {

        cityService.deleteCity(id);

        return ResponseEntity.noContent().build();
    }
}
