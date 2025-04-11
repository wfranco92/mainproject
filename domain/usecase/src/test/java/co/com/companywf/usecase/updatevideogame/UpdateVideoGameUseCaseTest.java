package co.com.companywf.usecase.updatevideogame;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.gateways.LocationRepository;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.gateway.StatusRepository;
import co.com.companywf.model.videogame.VideoGameRequest;
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
class UpdateVideoGameUseCaseTest {

    @Mock
    private VideogameRepository videogameRepository;

    @Mock
    private GenderRepository genderRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private UpdateVideoGameUseCase useCase;

    @Test
    void saveVideoGames(){

        VideoGameRequest videoGameRequest = new VideoGameRequest("videogame", "ROL", "PLAYED", "UBISOFT", "OTRO");

        when(genderRepository.getGenderById("c132db6c-447f-4c5f-b098-97efc5f95303")).thenReturn(Mono.just(Gender.builder().build()));
        when(statusRepository.getStatusById("6d0204ce-97d2-4083-8ff8-391dbab0bd4b")).thenReturn(Mono.just(Status.builder().build()));
        when(locationRepository.getLocationById("56bb9937-e810-4ac8-b625-fe508ef4ecdf")).thenReturn(Mono.just(Location.builder().build()));
        when(developerRepository.getDeveloperById("f184b778-7f85-4dcb-8431-3d5e9b57a6aa")).thenReturn(Mono.just(Developer.builder().build()));
        when(videogameRepository.updateVideoGame(any(), any())).thenReturn(Mono.just(Videogame.builder().id("AAA").name("videogame").gender("Rol").status("Jugado").developer("Ubisoft").location("Otro").createdAt(LocalDateTime.now()).build()));

        StepVerifier.create(useCase.execute("1", videoGameRequest))
                .expectNextMatches(videogame -> {
                    assertNotNull(videogame);
                    return true;
                }).expectComplete()
                .verify();
    }

    @Test
    void validateNonData(){
        VideoGameRequest videoGameRequest = new VideoGameRequest("videogame", "", "", "", "");

        StepVerifier.create(useCase.execute("1", videoGameRequest))
                .expectError(ValidateDataException.class)
                .verify();
    }

}