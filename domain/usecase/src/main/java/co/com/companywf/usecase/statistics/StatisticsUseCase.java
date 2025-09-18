package co.com.companywf.usecase.statistics;

import co.com.companywf.model.videogame.*;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
public class StatisticsUseCase {

    private final VideogameRepository videogameRepository;

    public Mono<ResponseStatistics> execute() {
        return Mono.zip(videogameRepository.getStatisticsAboutGender()
                        .collectMap(Statistics::getGender, Statistics::getAmount),
                        videogameRepository.getStatisticsAboutDeveloper()
                        .collectMap(StatisticsDeveloper::getDeveloper, StatisticsDeveloper::getAmount),
                        videogameRepository.getStatisticsAboutStatus()
                                .collectMap(StatisticsStatus::getStatus, StatisticsStatus::getAmount),
                        videogameRepository.getStatisticsAboutLocation()
                                .collectMap(StatisticsLocation::getLocation, StatisticsLocation::getAmount))
                .map(objects-> ResponseStatistics.builder()
                        .genders(objects.getT1())
                        .developers(objects.getT2())
                        .status(objects.getT3())
                        .location(objects.getT4())
                        .totalGames(getTotalValues(objects.getT1()))
                        .build());
    }

    private Integer getTotalValues(Map<String, Integer> amount){
        return amount.values().stream().reduce(0, Integer::sum);
    }
}
