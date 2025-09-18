package co.com.companywf.usecase.getallgender;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.gateway.GenderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllGenderUseCaseTest {

    @Mock
    private GenderRepository genderRepository;

    @InjectMocks
    private GetAllGenderUseCase getAllGenderUseCase;

    @Test
    void getAllGenderTest(){

        when(genderRepository.getAllGender()).thenReturn(Flux.fromIterable(getGenders()));

        StepVerifier.create(getAllGenderUseCase.execute())
                .expectNextCount(2)
                .verifyComplete();
    }

    private List<Gender> getGenders() {
        return List.of(Gender.builder().genderId("1").name("a").createdAt(LocalDateTime.now()).build(),
                Gender.builder().genderId("2").name("b").createdAt(LocalDateTime.now()).build());
    }
}