package qa.guru.countries.domain;

import qa.guru.countries.data.CountryEntity;

public record Country(
        String countryName,
        String countryCode
) {

    public CountryEntity toEntity(Country country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName(country.countryName);
        countryEntity.setCountryCode(country.countryCode);
        return countryEntity;
    }

    public static Country fromEntity(CountryEntity countryEntity) {
        return new Country(
                countryEntity.getCountryName(),
                countryEntity.getCountryCode()
        );
    }
}
