package co.com.companywf.usecase.getallvideogames;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllVideoGamesUseCase {

    private final VideogameRepository videogameRepository;

    public Flux<Videogame> execute (){
        return videogameRepository.getAllVideoGamesWithDescription();
    }
}
