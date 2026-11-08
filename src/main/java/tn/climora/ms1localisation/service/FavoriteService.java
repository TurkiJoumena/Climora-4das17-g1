
package tn.climora.ms1localisation.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.climora.ms1localisation.dto.FavoriteRequest;
import tn.climora.ms1localisation.entity.City;
import tn.climora.ms1localisation.entity.Favorite;
import tn.climora.ms1localisation.repository.CityRepository;
import tn.climora.ms1localisation.repository.FavoriteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final CityRepository cityRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            CityRepository cityRepository) {

        this.favoriteRepository = favoriteRepository;
        this.cityRepository = cityRepository;
    }

    public Favorite addFavorite(FavoriteRequest request) {

        City city = cityRepository.findById(request.cityId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ville introuvable"
                ));

        boolean alreadyExists =
                favoriteRepository.existsByUserIdAndCity_Id(
                        request.userId(),
                        request.cityId()
                );

        if (alreadyExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cette ville est déjà dans les favoris"
            );
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(request.userId());
        favorite.setDateAjout(LocalDate.now());
        favorite.setCity(city);

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUser(String userId) {
        return favoriteRepository.findByUserIdOrderByDateAjoutDesc(userId);
    }

    public Favorite getFavoriteById(Long id) {
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Favori introuvable"
                ));
    }

    public void deleteFavorite(Long id) {
        Favorite favorite = getFavoriteById(id);
        favoriteRepository.delete(favorite);
    }

    public List<City> getFollowedCities() {
        return favoriteRepository.findFollowedCities();
    }
}
