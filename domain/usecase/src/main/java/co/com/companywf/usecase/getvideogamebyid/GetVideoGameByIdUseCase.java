package co.com.companywf.usecase.getvideogamebyid;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import co.com.companywf.utils.Parser;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetVideoGameByIdUseCase {

    private final VideogameRepository videogameRepository;

    public Mono<Videogame> execute(String id) {
        return videogameRepository.getVideoGameById(id)
                .flatMap(Parser::getVideogameFormater);
    }
}
