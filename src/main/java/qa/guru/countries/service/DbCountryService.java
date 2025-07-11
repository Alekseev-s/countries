package qa.guru.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import qa.guru.countries.data.CountryEntity;
import qa.guru.countries.data.CountryRepository;
import qa.guru.countries.domain.Country;
import qa.guru.countries.domain.graphql.CountryGraphql;
import qa.guru.countries.domain.graphql.CountryInputGraphql;

import java.util.List;

@Service
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(Country::fromEntity)
                .toList();
    }

    @Override
    public Country addCountry(Country country) {
        CountryEntity countryEntity = countryRepository.save(country.toEntity(country));
        return Country.fromEntity(countryEntity);
    }

    @Override
    public Country editCountryName(Country country) {
        CountryEntity countryEntity = countryRepository.findByCountryCode(country.countryCode())
                .orElseThrow();
        countryEntity.setCountryName(country.countryName());
        return Country.fromEntity(countryRepository.save(countryEntity));
    }

    @Override
    public Page<CountryGraphql> allCountriesGraphql(Pageable pageable) {
        return countryRepository.findAll(pageable)
                .map(CountryGraphql::fromEntity);
    }

    @Override
    public CountryGraphql addCountryGraphql(CountryInputGraphql country) {
        CountryEntity countryEntity = countryRepository.save(country.toEntity(country));
        return CountryGraphql.fromEntity(countryEntity);
    }

    @Override
    public CountryGraphql editCountryNameGraphql(CountryInputGraphql country) {
        CountryEntity countryEntity = countryRepository.findByCountryCode(country.countryCode())
                .orElseThrow();
        countryEntity.setCountryName(country.countryName());
        return CountryGraphql.fromEntity(countryRepository.save(countryEntity));
    }
}
