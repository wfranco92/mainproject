package co.com.companywf.usecase.updatelocation;

import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateLocationUseCaseTest {

    @Mock
    private LocationRepository repository;

    @InjectMocks
    private UpdateLocationUseCase useCase;

    @Test
    void updateLocationTest(){

        LocationRequest request = LocationRequest.builder().name("updated").build();

        when(repository.updateLocation(any(), any())).thenReturn(Mono.just(Location.builder().locationId("123").name("updated").build()));

        StepVerifier.create(useCase.execute("123", request))
                .expectNextMatches(developer -> {
                    assertEquals("123", developer.getLocationId());
                    assertEquals(request.getName(), developer.getName());
                    return true;
                }).expectComplete().verify();
    }

    @Test
    void validateDataTest(){
        StepVerifier.create(useCase.execute("123", LocationRequest.builder().build()))
                .expectError(ValidateDataException.class);
    }

}