package co.com.companywf.usecase.getallvideogames;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllVideoGamesUseCaseTest {

    @Mock
    private VideogameRepository videogameRepository;

    @InjectMocks
    private GetAllVideoGamesUseCase videoGamesUseCase;

    @Test
    void getVideoGames(){

        Videogame videogame = new Videogame("id", "name", "gender", "status", "developer", "location", LocalDateTime.of(2000, 5, 12, 1, 11));

        Pageable pageable = PageRequest.of(1, 10, Sort.by(Sort.Order.asc("name")));

        when(videogameRepository.getAllVideoGamesWithDescription(pageable)).thenReturn(Flux.just(getVideoGame()));

        StepVerifier.create(videoGamesUseCase.execute("1", "10"))
                .expectNext(videogame)
                .expectComplete();
    }

    private Videogame getVideoGame(){
        return Videogame.builder()
                .id("id")
                .developer("developer")
                .name("name")
                .location("location")
                .status("status")
                .gender("gender")
                .createdAt(LocalDateTime.of(2000, 5, 12, 1, 11))
                .build();
    }

}