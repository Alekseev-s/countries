package qa.guru.countries.service;

import qa.guru.countries.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> allCountries();

    Country addCountry(Country country);

    Country editCountryName(Country country);
}
