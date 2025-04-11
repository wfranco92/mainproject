package co.com.companywf.usecase.savegender;

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

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveGenderUseCaseTest {

    @Mock
    private GenderRepository genderRepository;

    @InjectMocks
    private SaveGenderUseCase saveGenderUseCase;

    @Test
    void saveGenderTest(){

        GenderRequest request = GenderRequest.builder().name("gender").build();

        when(genderRepository.saveGender(any())).thenReturn(Mono.just(Gender.builder().genderId("ABC").name("Gender").createdAt(LocalDateTime.now()).build()));

        StepVerifier.create(saveGenderUseCase.execute(request))
                .expectNextMatches(gender -> {
                    assertNotNull(gender);
                    assertEquals("ABC", gender.getGenderId());
                    return true;
                }).expectComplete()
                .verify();
    }

    @Test
    void validateData(){
        GenderRequest request = GenderRequest.builder().name("").build();

        StepVerifier.create(saveGenderUseCase.execute(request))
                .expectError(ValidateDataException.class)
                .verify();
    }
}