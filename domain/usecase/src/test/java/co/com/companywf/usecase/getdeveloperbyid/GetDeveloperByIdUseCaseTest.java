package co.com.companywf.usecase.getdeveloperbyid;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
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
class GetDeveloperByIdUseCaseTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private GetDeveloperByIdUseCase getDeveloperByIdUseCase;

    @Test
    void getDeveloper(){

        when(developerRepository.getDeveloperById(any())).thenReturn(Mono.just(getDevelop()));

        StepVerifier.create(getDeveloperByIdUseCase.execute(any()))
                .expectNextMatches(developer -> {
                    assertNotNull(developer);
                    assertEquals("ABC",developer.getDeveloperId());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    private Developer getDevelop() {
        return Developer.builder()
                .developerId("ABC")
                .name("developer")
                .createdAt(LocalDateTime.now())
                .build();
    }

}