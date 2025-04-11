package co.com.companywf.usecase.savelocation;

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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveLocationUseCaseTest {

    @Mock
    private LocationRepository repository;

    @InjectMocks
    private SaveLocationUseCase useCase;

    @Test
    void saveLocationTest(){

        LocationRequest request = LocationRequest.builder().name("location").build();
        Location location = Location.builder().locationId("ID").name("location").createdAt(LocalDateTime.now()).build();

        when(repository.saveLocation(any())).thenReturn(Mono.just(location));

        StepVerifier.create(useCase.execute(request))
                .expectNext(location)
                .verifyComplete();
    }

    @Test
    void validateData(){
        LocationRequest request = new LocationRequest();
        StepVerifier.create(useCase.execute(request))
                .expectError(ValidateDataException.class)
                .verify();
    }
}