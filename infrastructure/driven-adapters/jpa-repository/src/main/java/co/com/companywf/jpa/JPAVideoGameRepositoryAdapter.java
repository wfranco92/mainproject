package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.*;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.videogame.*;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class JPAVideoGameRepositoryAdapter extends AdapterOperations<Videogame, VideoGameEntity, String, JPAVideoGameRepository> implements VideogameRepository
{

    public JPAVideoGameRepositoryAdapter(JPAVideoGameRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Videogame.class));
    }

    @Override
    public Flux<Videogame> getAllVideoGames() {
        return Flux.fromIterable(repository.findAll())
                .map(videoGameEntity -> mapper.map(videoGameEntity, Videogame.class));
    }

    @Override
    public Flux<Videogame> findVideoGamesWhitDescriptionLikeName(String name) {
        return Flux.fromIterable(repository.findVideoGamesWhitDescriptionLikeName(name))
                .map(videoGameDescriptionDTO -> mapper.map(videoGameDescriptionDTO, Videogame.class));
    }

    @Override
    public Mono<Page<Videogame>> getAllVideoGamesWithDescription(Pageable pageable) {
        return Mono.just(repository.findVideoGamesWhitDescription(pageable))
                .flatMap(page -> Flux.fromIterable(page.getContent())
                .map(videoGameDescriptionDTO -> mapper.map(videoGameDescriptionDTO, Videogame.class))
                        .collectList().map(videogames -> new PageImpl<>(videogames, pageable, page.getTotalElements())));
    }

    @Override
    public Flux<Videogame> saveAllVideoGames(List<VideoGameDB> videoGameRequestList) {
        return Flux.fromIterable(videoGameRequestList)
                .map(videoGameDB -> mapper.map(videoGameDB, VideoGameEntity.class))
                .collectList()
                .map(repository::saveAll)
                .flatMapMany(Flux::fromIterable)
                .map(videoGameEntity -> mapper.map(videoGameEntity, Videogame.class));
    }

    @Override
    public Mono<Videogame> getVideoGameById(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(videoGameEntity -> mapper.map(videoGameEntity, Videogame.class));
    }

    @Override
    public Mono<Videogame> updateVideoGame(String id, VideoGameDB videoGameRequest) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(videoGameEntity -> videoGameEntity.toBuilder()
                        .name(videoGameRequest.getName())
                        .gender(mapper.map(videoGameRequest.getGender(), GenderEntity.class))
                        .status(mapper.map(videoGameRequest.getStatus(), StatusEntity.class))
                        .location(mapper.map(videoGameRequest.getLocation(), LocationEntity.class))
                        .developer(mapper.map(videoGameRequest.getDeveloper(), DeveloperEntity.class))
                        .build())
                .map(repository::save)
                .map(videoGameEntity -> mapper.map(videoGameEntity, Videogame.class));
    }

    @Override
    public Mono<Videogame> deleteVideoGameById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(videoGameEntity -> {
                    repository.deleteById(id); return videoGameEntity;}))
                .map(videoGameEntity -> mapper.map(videoGameEntity, Videogame.class));
    }

    @Override
    public Flux<Statistics> getStatisticsAboutGender() {
        return Mono.justOrEmpty(repository.getStatisticsAboutGender())
                .flatMapMany(Flux::fromIterable)
                .map(objects -> Statistics.builder()
                        .gender(String.valueOf(objects[0]))
                        .amount(Integer.parseInt(String.valueOf(objects[1])))
                        .build());
    }

    @Override
    public Flux<StatisticsDeveloper> getStatisticsAboutDeveloper() {
        return Mono.justOrEmpty(repository.getStatisticsAboutDeveloper())
                .flatMapMany(Flux::fromIterable)
                .map(objects -> StatisticsDeveloper.builder()
                        .developer(String.valueOf(objects[0]))
                        .amount(Integer.parseInt(String.valueOf(objects[1])))
                        .build());
    }

    @Override
    public Flux<StatisticsStatus> getStatisticsAboutStatus() {
        return Mono.justOrEmpty(repository.getStatisticsAboutStatus())
                .flatMapMany(Flux::fromIterable)
                .map(objects -> StatisticsStatus.builder()
                        .status(String.valueOf(objects[0]))
                        .amount(Integer.parseInt(String.valueOf(objects[1])))
                        .build());
    }

    @Override
    public Flux<StatisticsLocation> getStatisticsAboutLocation() {
        return Mono.justOrEmpty(repository.getStatisticsAboutLocation())
                .flatMapMany(Flux::fromIterable)
                .map(objects -> StatisticsLocation.builder()
                        .location(String.valueOf(objects[0]))
                        .amount(Integer.parseInt(String.valueOf(objects[1])))
                        .build());
    }
}
