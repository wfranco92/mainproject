package co.com.companywf.usecase.searchvideogamebyname;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class SearchVideoGameByNameUseCase {

    private final VideogameRepository videogameRepository;

    public Flux<Videogame> execute(String name){
        return videogameRepository.findVideoGamesWhitDescriptionLikeName(name);
    }
}
