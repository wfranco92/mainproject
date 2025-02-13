package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.videogame.Videogame;
import reactor.core.publisher.Flux;

public interface VideogameRepository {
    Flux<Videogame> getAllVideoGames();
    Flux<Videogame> getAllVideoGamesWithDescription();
}
