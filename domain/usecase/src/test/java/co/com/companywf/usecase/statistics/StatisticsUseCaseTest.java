package co.com.companywf.usecase.statistics;

import co.com.companywf.model.videogame.Statistics;
import co.com.companywf.model.videogame.StatisticsDeveloper;
import co.com.companywf.model.videogame.StatisticsLocation;
import co.com.companywf.model.videogame.StatisticsStatus;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsUseCaseTest {

    @Mock
    private VideogameRepository repository;

    @InjectMocks
    private StatisticsUseCase useCase;

    @BeforeEach
    public void setUp() {
        when(repository.getStatisticsAboutGender()).thenReturn(Flux.just(Statistics.builder().gender("gender").amount(2).build()));
        when(repository.getStatisticsAboutLocation()).thenReturn(Flux.just(StatisticsLocation.builder().location("location").amount(1).build()));
        when(repository.getStatisticsAboutStatus()).thenReturn(Flux.just(StatisticsStatus.builder().status("status").amount(1).build()));
        when(repository.getStatisticsAboutDeveloper()).thenReturn(Flux.just(StatisticsDeveloper.builder().developer("developer").amount(1).build()));

    }

    @Test
    void validateStatisticsTest(){


        StepVerifier.create(useCase.execute())
                .expectNextMatches(responseStatistics -> {
                    assertNotNull(responseStatistics.getStatus());
                    assertNotNull(responseStatistics.getLocation());
                    assertNotNull(responseStatistics.getDevelopers());
                    assertNotNull(responseStatistics.getGenders());
                    return true;
                }).expectComplete().verify();
    }

    @Test
    void validateTotalValuesTest(){
        StepVerifier.create(useCase.execute())
                .expectNextMatches(responseStatistics -> {
                    assertEquals(2, responseStatistics.getTotalGames());
                    return true;
                }).expectComplete().verify();
    }
}