package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.model.videogame.Videogame;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VideogameRepository {
    Flux<Videogame> getAllVideoGames();
    Flux<Videogame> getAllVideoGamesWithDescription();
    Flux<Videogame> saveAllVideoGames(List<VideoGameDB> videoGameRequestList);
    Mono<Videogame> getVideoGameById(String id);
    Mono<Videogame> updateVideoGame(String id, VideoGameDB videoGameDB);
}
