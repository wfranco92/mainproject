package co.com.companywf.usecase.getalllocation;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.gateways.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllLocationUseCaseTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private GetAllLocationUseCase getAllLocationUseCase;

    @Test
    void getLocations(){

        when(locationRepository.getAllLocation()).thenReturn(Flux.just(getLocation()));

        StepVerifier.create(getAllLocationUseCase.execute())
                .expectNextMatches(location -> {
                    assertNotNull(location);
                    return true;
                })
                .expectComplete()
                .verify();
    }

    private Location getLocation() {
        return Location.builder().build();
    }
}