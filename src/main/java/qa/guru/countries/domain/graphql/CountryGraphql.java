package qa.guru.countries.domain.graphql;

import qa.guru.countries.data.CountryEntity;

import java.util.UUID;

public record CountryGraphql(
        UUID id,
        String countryName,
        String countryCode
) {

    public static CountryGraphql fromEntity(CountryEntity countryEntity) {
        return new CountryGraphql(
                countryEntity.getId(),
                countryEntity.getCountryName(),
                countryEntity.getCountryCode()
        );
    }
}
