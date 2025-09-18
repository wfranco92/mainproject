package co.com.companywf.usecase.getallvideogames;

import co.com.companywf.model.videogame.VideoGameListResponse;
import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@RequiredArgsConstructor
public class GetAllVideoGamesUseCase {

    private final VideogameRepository videogameRepository;

    public Mono<VideoGameListResponse> execute (String page, String size){
        Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), Sort.by(Sort.Order.asc("name")));
        return videogameRepository.getAllVideoGamesWithDescription(pageable)
                .map(pageResponse -> getVideoGamelistReponse(Integer.parseInt(page), Integer.parseInt(size), pageResponse));
    }

    private VideoGameListResponse getVideoGamelistReponse(int pages, int size, Page<Videogame> page){
        return VideoGameListResponse.builder()
                .totalItems(page.getTotalElements())
                .totalPages(Math.floorDiv((int)page.getTotalElements(), size))
                .currentPage(pages)
                .videoGames(page.getContent())
                .build();
    }
}
