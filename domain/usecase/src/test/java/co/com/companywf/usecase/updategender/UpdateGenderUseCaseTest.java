package co.com.companywf.usecase.updategender;

import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
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
class UpdateGenderUseCaseTest {

    @Mock
    private GenderRepository repository;

    @InjectMocks
    private UpdateGenderUseCase useCase;

    @Test
    void updateGenderTest(){

        GenderRequest request = GenderRequest.builder().name("updated").build();

        when(repository.updateGender(any(), any())).thenReturn(Mono.just(Gender.builder().genderId("123").name("updated").build()));

        StepVerifier.create(useCase.execute("123", request))
                .expectNextMatches(developer -> {
                    assertEquals("123", developer.getGenderId());
                    assertEquals(request.getName(), developer.getName());
                    return true;
                }).expectComplete().verify();
    }

    @Test
    void validateDataTest(){
        StepVerifier.create(useCase.execute("123", GenderRequest.builder().build()))
                .expectError(ValidateDataException.class);
    }

}