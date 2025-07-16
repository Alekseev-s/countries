package qa.guru.countries.service;

import com.google.protobuf.Empty;
import guru.qa.grpc.country.*;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import qa.guru.countries.domain.graphql.CountryGraphql;
import qa.guru.countries.domain.graphql.CountryInputGraphql;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class GrpcCountryService extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryService countriesService;

    public GrpcCountryService(CountryService countriesService) {
        this.countriesService = countriesService;
    }

    @Override
    public void allCountries(Empty request, StreamObserver<CountryListResponse> responseObserver) {
        List<CountryGraphql> countries = countriesService.allCountriesGraphql();
        List<CountryResponse> response = countries.stream()
                .map(country -> CountryResponse.newBuilder()
                        .setId(country.id().toString())
                        .setCountryName(country.countryName())
                        .setCountryCode(country.countryCode())
                        .build())
                .collect(Collectors.toList());

        CountryListResponse countryListResponse = CountryListResponse
                .newBuilder()
                .addAllCountries(response)
                .build();
        responseObserver.onNext(countryListResponse);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<CountryRequest> addCountries(StreamObserver<CountResponse> responseObserver) {
        AtomicInteger count = new AtomicInteger();

        return new StreamObserver<>() {

            @Override
            public void onNext(CountryRequest countryRequest) {
                countriesService.addCountryGraphql(
                        new CountryInputGraphql(
                                countryRequest.getCountryCode(),
                                countryRequest.getCountryName()
                        )
                );
                count.incrementAndGet();
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                CountResponse response = CountResponse.newBuilder()
                        .setCount(count.get())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void updateCountry(CountryRequest request, StreamObserver<CountryResponse> responseObserver) {

        CountryGraphql updatedCountry = countriesService.editCountryNameGraphql(
                new CountryInputGraphql(
                        request.getCountryCode(),
                        request.getCountryName()
                )
        );

        CountryResponse response = CountryResponse.newBuilder()
                .setId(updatedCountry.id().toString())
                .setCountryName(updatedCountry.countryName())
                .setCountryCode(updatedCountry.countryCode())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
