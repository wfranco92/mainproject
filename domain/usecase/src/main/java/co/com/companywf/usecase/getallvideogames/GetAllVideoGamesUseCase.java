package co.com.companywf.usecase.getallvideogames;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@RequiredArgsConstructor
public class GetAllVideoGamesUseCase {

    private final VideogameRepository videogameRepository;

    public Flux<Videogame> execute (String page, String size){
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(Sort.Order.asc("name")));
        return videogameRepository.getAllVideoGamesWithDescription(pageable)
                .sort(Comparator.comparing(Videogame::getName));
    }
}
