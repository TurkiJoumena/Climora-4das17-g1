
package tn.climora.ms1localisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.climora.ms1localisation.entity.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContainingIgnoreCase(String name);

    List<City> findByCountryIgnoreCase(String country);
}
