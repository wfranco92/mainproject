package co.com.companywf.api;

import co.com.companywf.api.dto.*;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.usecase.getalldeveloper.GetAllDeveloperUseCase;
import co.com.companywf.usecase.getallgender.GetAllGenderUseCase;
import co.com.companywf.usecase.getallstatus.GetAllStatusUseCase;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getdeveloperbyid.GetDeveloperByIdUseCase;
import co.com.companywf.usecase.getgenderbyid.GetGenderByIdUseCase;
import co.com.companywf.usecase.getstatusbyid.GetStatusByIdUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import co.com.companywf.usecase.saveallvideogames.SaveAllVideoGamesUseCase;
import co.com.companywf.usecase.savedeveloper.SaveDeveloperUseCase;
import co.com.companywf.usecase.savegender.SaveGenderUseCase;
import co.com.companywf.usecase.savestatus.SaveStatusUseCase;
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
private final SaveStatusUseCase saveStatusUseCase;
private final GetAllStatusUseCase getAllStatusUseCase;
private final GetStatusByIdUseCase getStatusByIdUseCase;
private final SaveDeveloperUseCase saveDeveloperUseCase;
private final GetAllDeveloperUseCase getAllDeveloperUseCase;
private final GetDeveloperByIdUseCase getDeveloperByIdUseCase;

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

    public Mono<ServerResponse> listenSaveStatus(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(StatusRequestDTO.class)
                .map(statusRequestDTO -> mapper.map(statusRequestDTO, StatusRequest.class))
                .flatMap(saveStatusUseCase::execute)
                .flatMap(status -> ServerResponse.ok().bodyValue(status));
    }

    public Mono<ServerResponse> listenGetAllStatus(ServerRequest serverRequest) {
        return getAllStatusUseCase.execute()
                .collectList()
                .flatMap(status -> ServerResponse.ok().bodyValue(status));
    }

    public Mono<ServerResponse> listenGetStatusById(ServerRequest serverRequest) {
        return getStatusByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(status -> ServerResponse.ok().bodyValue(status));
    }

    public Mono<ServerResponse> listenSaveDeveloper(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(DeveloperRequestDTO.class)
                .map(developerRequestDTO -> mapper.map(developerRequestDTO, DeveloperRequest.class))
                .flatMap(saveDeveloperUseCase::execute)
                .flatMap(developer -> ServerResponse.ok().bodyValue(developer));
    }

    public Mono<ServerResponse> listenGetDeveloperById(ServerRequest serverRequest) {
        return getDeveloperByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(developer -> ServerResponse.ok().bodyValue(developer));
    }

    public Mono<ServerResponse> listenGetAllDeveloper(ServerRequest serverRequest) {
        return getAllDeveloperUseCase.execute()
                .collectList()
                .flatMap(developers -> ServerResponse.ok().bodyValue(developers));
    }
}
