package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.VideoGameEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

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
    public Flux<Videogame> getAllVideoGamesWithDescription() {
        return Flux.fromIterable(repository.findVideoGamesWhitDescription())
                .map(videoGameDescriptionDTO -> mapper.map(videoGameDescriptionDTO, Videogame.class));
    }
}
