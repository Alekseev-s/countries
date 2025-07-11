package qa.guru.countries.controller.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qa.guru.countries.domain.Country;
import qa.guru.countries.domain.graphql.CountryGraphql;
import qa.guru.countries.service.CountryService;

import java.util.List;

@Controller
public class CountryQueryController {

    private final CountryService countryService;

    @Autowired
    public CountryQueryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @QueryMapping
    public Slice<CountryGraphql> countries(@Argument int page, @Argument int size) {
        return countryService.allCountriesGraphql(PageRequest.of(
                page,
                size
        ));
    }
}
