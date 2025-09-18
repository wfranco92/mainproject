package co.com.companywf.usecase.getvideogamebyid;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetVideoGameByIdUseCaseTest {

    @Mock
    private VideogameRepository videogameRepository;

    @InjectMocks
    private GetVideoGameByIdUseCase videoGameByIdUseCase;

    @Test
    void videoGameById(){

        when(videogameRepository.getVideoGameById(any())).thenReturn(Mono.just(getVideoGame()));

        StepVerifier.create(videoGameByIdUseCase.execute(any()))
                .expectNextMatches(videogame -> {
                    assertNotNull(videogame);
                    return true;
                }).expectComplete()
                .verify();
    }

    private Videogame getVideoGame(){
        return Videogame.builder()
                .id("ID")
                .status("status")
                .developer("developer")
                .gender("gender")
                .location("location")
                .name("videogame")
                .createdAt(LocalDateTime.now())
                .build();
    }
}