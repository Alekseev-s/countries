package qa.guru.countries.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import qa.guru.countries.domain.Country;
import qa.guru.countries.domain.graphql.CountryGraphql;
import qa.guru.countries.domain.graphql.CountryInputGraphql;

import java.util.List;

public interface CountryService {

    List<Country> allCountries();

    Country addCountry(Country country);

    Country editCountryName(Country country);

    Slice<CountryGraphql> allCountriesGraphql(Pageable pageable);

    CountryGraphql addCountryGraphql(CountryInputGraphql country);

    CountryGraphql editCountryNameGraphql(CountryInputGraphql country);
}
