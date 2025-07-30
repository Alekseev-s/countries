package qa.guru.countries.service.soap;

import guru.qa.xml.country.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import qa.guru.countries.config.AppConfig;
import qa.guru.countries.service.CountryService;

@Endpoint
public class CountryEndpoint {

    private final CountryService countryService;

    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "allRequest")
    @ResponsePayload
    public CountriesResponse all() {
        CountriesResponse response = new CountriesResponse();
        response.getCountries().addAll(countryService.allCountries().stream()
                .map(country -> {
                    Country countryXml = new Country();
                    countryXml.setName(country.countryName());
                    countryXml.setCode(country.countryCode());
                    return countryXml;
                })
                .toList());
        return response;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "addCountryRequest")
    @ResponsePayload
    public CountryResponse add(@RequestPayload AddCountryRequest request) {
        qa.guru.countries.domain.Country country = countryService.addCountry(new qa.guru.countries.domain.Country(
                request.getCountry().getName(),
                request.getCountry().getCode())
        );
        CountryResponse countryResponse = new CountryResponse();
        Country countryXml = new Country();
        countryXml.setName(country.countryName());
        countryXml.setCode(country.countryCode());
        countryResponse.setCountry(countryXml);
        return countryResponse;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "editCountryNameRequest")
    @ResponsePayload
    public CountryResponse editCountryName(@RequestPayload EditCountryNameRequest request) {
        qa.guru.countries.domain.Country country = countryService.editCountryName(new qa.guru.countries.domain.Country(
                request.getCountry().getName(),
                request.getCountry().getCode())
        );
        CountryResponse countryResponse = new CountryResponse();
        Country countryXml = new Country();
        countryXml.setName(country.countryName());
        countryXml.setCode(country.countryCode());
        countryResponse.setCountry(countryXml);
        return countryResponse;
    }
}
