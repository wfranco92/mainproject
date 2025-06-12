package co.com.companywf.usecase.updatedeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.exception.ValidateDataException;
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
class UpdateDeveloperUseCaseTest {

    @Mock
    private DeveloperRepository repository;

    @InjectMocks
    private UpdateDeveloperUseCase useCase;

    @Test
    void updateDeveloperTest(){

        DeveloperRequest request = DeveloperRequest.builder().name("updated").build();

        when(repository.updateDeveloper(any(), any())).thenReturn(Mono.just(Developer.builder().developerId("123").name("updated").build()));

        StepVerifier.create(useCase.execute("123", request))
                .expectNextMatches(developer -> {
                    assertEquals("123", developer.getDeveloperId());
                    assertEquals(request.getName(), developer.getName());
                    return true;
                }).expectComplete().verify();
    }

    @Test
    void validateDataTest(){
        StepVerifier.create(useCase.execute("123", DeveloperRequest.builder().build()))
                .expectError(ValidateDataException.class);
    }
}