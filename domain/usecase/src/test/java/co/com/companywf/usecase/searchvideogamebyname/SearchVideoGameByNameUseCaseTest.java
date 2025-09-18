package co.com.companywf.usecase.searchvideogamebyname;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVideoGameByNameUseCaseTest {

    @Mock
    private VideogameRepository repository;

    @InjectMocks
    private SearchVideoGameByNameUseCase useCase;

    @Test
    void searchVideogameByNameTest(){

        when(repository.findVideoGamesWhitDescriptionLikeName(any())).thenReturn(Flux.fromIterable(getVideoGames()));

        StepVerifier.create(useCase.execute(any()))
                .expectNextCount(2)
                .verifyComplete();
    }

    private List<Videogame> getVideoGames(){
        return List.of(Videogame.builder().build(),Videogame.builder().build());
    }
}