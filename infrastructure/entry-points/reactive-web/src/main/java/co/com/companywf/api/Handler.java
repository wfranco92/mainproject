package co.com.companywf.api;

import co.com.companywf.api.dto.GenderRequestDTO;
import co.com.companywf.api.dto.VideoGameRequestDTO;
import co.com.companywf.api.dto.VideoGameResponseDTO;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.usecase.getallgender.GetAllGenderUseCase;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getgenderbyid.GetGenderByIdUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import co.com.companywf.usecase.saveallvideogames.SaveAllVideoGamesUseCase;
import co.com.companywf.usecase.savegender.SaveGenderUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

private final GetAllVideoGamesUseCase getAllVideoGamesUseCase;
private final GetVideoGameByIdUseCase getVideoGameByIdUseCase;
private final SaveAllVideoGamesUseCase saveAllVideoGamesUseCase;
private final SaveGenderUseCase saveGenderUseCase;
private final GetGenderByIdUseCase getGenderByIdUseCase;
private final GetAllGenderUseCase getAllGenderUseCase;

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

    public Mono<ServerResponse> listenGetVideoGameById(ServerRequest serverRequest) {
        return getVideoGameByIdUseCase.execute(serverRequest.pathVariable("id"))
                .map(videogameEntity -> mapper.map(videogameEntity, VideoGameResponseDTO.class))
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame));
    }

    public Mono<ServerResponse> listenSaveGender(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(GenderRequestDTO.class)
                .map(genderRequestDTO -> mapper.map(genderRequestDTO, GenderRequest.class))
                .flatMap(saveGenderUseCase::execute)
                .flatMap(gender -> ServerResponse.ok().bodyValue(gender));
    }

    public Mono<ServerResponse> listenGetGenderById(ServerRequest serverRequest) {
        return getGenderByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(gender -> ServerResponse.ok().bodyValue(gender));
    }

    public Mono<ServerResponse> listenGetAllGender(ServerRequest serverRequest) {
        return getAllGenderUseCase.execute()
                .collectList()
                .flatMap(genders -> ServerResponse.ok().bodyValue(genders));
    }
}
