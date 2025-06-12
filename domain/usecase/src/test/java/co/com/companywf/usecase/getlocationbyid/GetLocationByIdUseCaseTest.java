package co.com.companywf.usecase.getlocationbyid;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.gateways.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetLocationByIdUseCaseTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private GetLocationByIdUseCase getLocationByIdUseCase;

    @Test
    void getLocationById(){

        when(locationRepository.getLocationById(any())).thenReturn(Mono.just(getLocation()));

        StepVerifier.create(getLocationByIdUseCase.execute(any()))
                .expectNextMatches(location -> {
                    assertNotNull(location);
                    assertEquals("MNB", location.getLocationId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    private Location getLocation(){
        return Location.builder()
                .locationId("MNB")
                .name("location")
                .createdAt(LocalDateTime.now())
                .build();
    }
}