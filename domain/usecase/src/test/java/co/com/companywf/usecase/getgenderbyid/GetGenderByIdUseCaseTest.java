package co.com.companywf.usecase.getgenderbyid;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.gateway.GenderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetGenderByIdUseCaseTest {

    @Mock
    private GenderRepository genderRepository;

    @InjectMocks
    private GetGenderByIdUseCase getGenderByIdUseCase;

    @Test
    void getGender(){

        when(genderRepository.getGenderById(any())).thenReturn(Mono.just(Gender.builder().genderId("QWE").name("gender").build()));

        StepVerifier.create(getGenderByIdUseCase.execute(any()))
                .expectNextMatches(gender -> {
                    assertNotNull(gender);
                    assertNotEquals("123", gender.getGenderId());
                    return true;
                })
                .expectComplete()
                .verify();
    }


}