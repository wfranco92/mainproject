package co.com.companywf.api;

import co.com.companywf.api.dto.VideoGameRequestDTO;
import co.com.companywf.api.dto.VideoGameResponseDTO;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import co.com.companywf.usecase.saveallvideogames.SaveAllVideoGamesUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class Handler {

private final GetAllVideoGamesUseCase getAllVideoGamesUseCase;
private final GetVideoGameByIdUseCase getVideoGameByIdUseCase;
private final SaveAllVideoGamesUseCase saveAllVideoGamesUseCase;

private final ObjectMapper mapper;

    public Mono<ServerResponse> listenAllVideoGames(ServerRequest serverRequest) {
        return getAllVideoGamesUseCase.execute()
                .collectList()
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame));
//        return ServerResponse.ok().body(getAllVideoGamesUseCase.execute(), Videogame.class);
    }

    public Mono<ServerResponse> listenSaveVideoGame(ServerRequest serverRequest) {
        return serverRequest.bodyToFlux(VideoGameRequestDTO.class)
                .map(videoGameRequestDTO -> mapper.map(videoGameRequestDTO, VideoGameRequest.class))
                .collectList()
                .flatMapMany(saveAllVideoGamesUseCase::execute)
                .map(videogames -> mapper.map(videogames, VideoGameResponseDTO.class))
                .collectList()
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame));
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenSaveGender(ServerRequest serverRequest) {
        return null;
    }
}
