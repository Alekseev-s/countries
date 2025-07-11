package qa.guru.countries.controller.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import qa.guru.countries.domain.Country;
import qa.guru.countries.domain.graphql.CountryGraphql;
import qa.guru.countries.domain.graphql.CountryInputGraphql;
import qa.guru.countries.service.CountryService;

import java.util.List;

@Controller
public class CountryMutationController {

    private final CountryService countryService;

    @Autowired
    public CountryMutationController(CountryService countryService) {
        this.countryService = countryService;
    }

    @MutationMapping
    public CountryGraphql addCountry(@Argument CountryInputGraphql input) {
        return countryService.addCountryGraphql(input);
    }

    @MutationMapping
    public CountryGraphql editCountryName(@Argument CountryInputGraphql input) {
        return countryService.editCountryNameGraphql(input);
    }
}
