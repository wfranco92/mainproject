package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.videogame.Videogame;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VideogameRepository {
    Flux<Videogame> getAllVideoGames();
    Flux<Videogame> getAllVideoGamesWithDescription(Pageable pageable);
    Flux<Videogame> saveAllVideoGames(List<VideoGameDB> videoGameRequestList);
    Mono<Videogame> getVideoGameById(String id);
    Mono<Videogame> updateVideoGame(String id, VideoGameDB videoGameDB);
    Mono<Videogame> deleteVideoGameById(String id);
}
