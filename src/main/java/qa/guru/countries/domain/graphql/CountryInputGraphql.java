package qa.guru.countries.domain.graphql;

import qa.guru.countries.data.CountryEntity;

public record CountryInputGraphql(
        String countryName,
        String countryCode
) {

    public CountryEntity toEntity(CountryInputGraphql country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName(country.countryName);
        countryEntity.setCountryCode(country.countryCode);
        return countryEntity;
    }
}
