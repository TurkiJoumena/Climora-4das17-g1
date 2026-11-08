
package tn.climora.ms1localisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.climora.ms1localisation.entity.City;
import tn.climora.ms1localisation.entity.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUserIdAndCity_Id(String userId, Long cityId);

    List<Favorite> findByUserIdOrderByDateAjoutDesc(String userId);

    @Query("SELECT DISTINCT f.city FROM Favorite f")
    List<City> findFollowedCities();
}
