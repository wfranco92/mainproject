package co.com.companywf.usecase.savedeveloper;

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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveDeveloperUseCaseTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private SaveDeveloperUseCase saveDeveloperUseCase;

    @Test
    void saveDeveloper(){

        DeveloperRequest request = DeveloperRequest.builder().name("develop").build();

        when(developerRepository.saveDeveloper(any())).thenReturn(Mono.just(Developer.builder().developerId("DEV").name("develop").createdAt(LocalDateTime.now()).build()));

        StepVerifier.create(saveDeveloperUseCase.execute(request))
                .expectNextMatches(developer -> {
                    assertNotNull(developer);
                    assertEquals("develop", developer.getName());
                    return true;
                }).expectComplete()
                .verify();
    }

    @Test
    void validateData(){
        DeveloperRequest request = new DeveloperRequest();

        StepVerifier.create(saveDeveloperUseCase.execute(request))
                .expectError(ValidateDataException.class)
                .verify();
    }

}