
package tn.climora.ms1localisation.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.climora.ms1localisation.entity.City;
import tn.climora.ms1localisation.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public List<City> searchCitiesByName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name);
    }

    public List<City> getCitiesByCountry(String country) {
        return cityRepository.findByCountryIgnoreCase(country);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {

        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ville introuvable"
                ));

        existingCity.setName(updatedCity.getName());
        existingCity.setCountry(updatedCity.getCountry());
        existingCity.setLatitude(updatedCity.getLatitude());
        existingCity.setLongitude(updatedCity.getLongitude());
        existingCity.setTimezone(updatedCity.getTimezone());

        return cityRepository.save(existingCity);
    }

    public void deleteCity(Long id) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ville introuvable"
                ));

        cityRepository.delete(city);
    }
}
