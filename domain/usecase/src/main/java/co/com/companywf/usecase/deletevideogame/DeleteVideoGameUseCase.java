package co.com.companywf.usecase.deletevideogame;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteVideoGameUseCase {

    private final VideogameRepository videogameRepository;

    public Mono<Videogame> execute(String id){
        return videogameRepository.deleteVideoGameById(id);
    }
}
