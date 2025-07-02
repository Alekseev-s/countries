package qa.guru.countries.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qa.guru.countries.domain.Country;
import qa.guru.countries.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<Country> allCountries() {
        return countryService.allCountries();
    }

    @PostMapping("/add")
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PatchMapping("/edit")
    public Country editCountryName(@RequestBody Country country) {
        return countryService.editCountryName(country);
    }
}
