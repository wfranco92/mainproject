package co.com.companywf.usecase.deletevideogame;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteVideoGameUseCaseTest {

    @Mock
    private VideogameRepository repository;

    @InjectMocks
    private DeleteVideoGameUseCase useCase;

    @Test
    void deleteVideoGameTest(){

        when(repository.deleteVideoGameById(any())).thenReturn(Mono.just(Videogame.builder().build()));

        StepVerifier.create(useCase.execute(any()))
                .expectNextMatches(videogame -> {
                    assertNotNull(videogame);
                    return true;
                }).expectComplete().verify();
    }
}