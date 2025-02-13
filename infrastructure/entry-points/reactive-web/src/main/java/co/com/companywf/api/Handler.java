package co.com.companywf.api;

import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
private  final GetAllVideoGamesUseCase getAllVideoGamesUseCase;
private  final GetVideoGameByIdUseCase getVideoGameByIdUseCase;

    public Mono<ServerResponse> listenAllVideoGames(ServerRequest serverRequest) {
        return getAllVideoGamesUseCase.execute()
                .collectList()
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame));
//        return ServerResponse.ok().body(getAllVideoGamesUseCase.execute(), Videogame.class);
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
