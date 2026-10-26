package com.mun.mundelegation.service;

import com.mun.mundelegation.Country;
import com.mun.mundelegation.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public void addCountry(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            throw new IllegalArgumentException("This country is already registered.");
        }

        countryRepository.save(country);
    }

    public void updateCountry(Country country) {
        if (countryRepository.existsByNameAndNotId(
                country.getName(), country.getId())) {
            throw new IllegalArgumentException("This country is already registered.");
        }

        countryRepository.update(country);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}

