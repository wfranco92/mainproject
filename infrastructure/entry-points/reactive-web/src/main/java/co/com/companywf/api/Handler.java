package co.com.companywf.api;

import co.com.companywf.api.dto.*;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.usecase.deletevideogame.DeleteVideoGameUseCase;
import co.com.companywf.usecase.getalldeveloper.GetAllDeveloperUseCase;
import co.com.companywf.usecase.getallgender.GetAllGenderUseCase;
import co.com.companywf.usecase.getalllocation.GetAllLocationUseCase;
import co.com.companywf.usecase.getallstatus.GetAllStatusUseCase;
import co.com.companywf.usecase.getallvideogames.GetAllVideoGamesUseCase;
import co.com.companywf.usecase.getdeveloperbyid.GetDeveloperByIdUseCase;
import co.com.companywf.usecase.getgenderbyid.GetGenderByIdUseCase;
import co.com.companywf.usecase.getlocationbyid.GetLocationByIdUseCase;
import co.com.companywf.usecase.getstatusbyid.GetStatusByIdUseCase;
import co.com.companywf.usecase.getvideogamebyid.GetVideoGameByIdUseCase;
import co.com.companywf.usecase.saveallvideogames.SaveAllVideoGamesUseCase;
import co.com.companywf.usecase.savedeveloper.SaveDeveloperUseCase;
import co.com.companywf.usecase.savegender.SaveGenderUseCase;
import co.com.companywf.usecase.savelocation.SaveLocationUseCase;
import co.com.companywf.usecase.savestatus.SaveStatusUseCase;
import co.com.companywf.usecase.statistics.StatisticsUseCase;
import co.com.companywf.usecase.updatedeveloper.UpdateDeveloperUseCase;
import co.com.companywf.usecase.updategender.UpdateGenderUseCase;
import co.com.companywf.usecase.updatelocation.UpdateLocationUseCase;
import co.com.companywf.usecase.updatestatus.UpdateStatusUseCase;
import co.com.companywf.usecase.updatevideogame.UpdateVideoGameUseCase;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private final GetLocationByIdUseCase getLocationByIdUseCase;
    private final GetAllLocationUseCase getAllLocationUseCase;
    private final SaveLocationUseCase saveLocationUseCase;
    private final UpdateGenderUseCase updateGenderUseCase;
    private final UpdateVideoGameUseCase updateVideoGameUseCase;
    private final UpdateStatusUseCase updateStatusUseCase;
    private final UpdateDeveloperUseCase updateDeveloperUseCase;
    private final UpdateLocationUseCase updateLocationUseCase;
    private final DeleteVideoGameUseCase deleteVideoGameUseCase;
    private final StatisticsUseCase statisticsUseCase;

    private final ObjectMapper mapper;
    private static final String MESSAGE = "message";
    private static final String PATH_NOT_FOUND = "path not found.";

    public Mono<ServerResponse> listenAllVideoGames(ServerRequest serverRequest) {
        return getAllVideoGamesUseCase.execute(serverRequest.queryParam("page").orElse("0"), serverRequest.queryParam("size").orElse("10"))
                .collectList()
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
//        return ServerResponse.ok().body(getAllVideoGamesUseCase.execute(), Videogame.class);
    }

    public Mono<ServerResponse> listenSaveVideoGame(ServerRequest serverRequest) {
        return serverRequest.bodyToFlux(VideoGameRequestDTO.class)
                .map(videoGameRequestDTO -> mapper.map(videoGameRequestDTO, VideoGameRequest.class))
                .collectList()
                .flatMapMany(saveAllVideoGamesUseCase::execute)
                .map(videogames -> mapper.map(videogames, VideoGameResponseDTO.class))
                .collectList()
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetVideoGameById(ServerRequest serverRequest) {
        return getVideoGameByIdUseCase.execute(serverRequest.pathVariable("id"))
                .map(videogameEntity -> mapper.map(videogameEntity, VideoGameResponseDTO.class))
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenPUTVideoGame(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(VideoGameRequestDTO.class)
                .map(videoGameRequestDTO -> mapper.map(videoGameRequestDTO, VideoGameRequest.class))
                .flatMap(videoGameRequest -> updateVideoGameUseCase.execute(serverRequest.pathVariable("id"), videoGameRequest))
                .filter(Objects::nonNull)
                .map(videogames -> mapper.map(videogames, VideoGameResponseDTO.class))
                .flatMap(videogame -> ServerResponse.ok().bodyValue(videogame))
                .switchIfEmpty(Mono.defer(() -> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenDeleteVideoGame(ServerRequest serverRequest) {
        return deleteVideoGameUseCase.execute(serverRequest.pathVariable("id"))
                .filter(Objects::nonNull)
                .map(videogame -> mapper.map(videogame, VideoGameResponseDTO.class))
                .flatMap(videoGameResponseDTO -> ServerResponse.ok().bodyValue(videoGameResponseDTO))
                .switchIfEmpty(Mono.defer(() -> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenSaveGender(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(GenderRequestDTO.class)
                .map(genderRequestDTO -> mapper.map(genderRequestDTO, GenderRequest.class))
                .flatMap(saveGenderUseCase::execute)
                .flatMap(gender -> ServerResponse.ok().bodyValue(gender))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetGenderById(ServerRequest serverRequest) {
        return getGenderByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(gender -> ServerResponse.ok().bodyValue(gender))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetAllGender(ServerRequest serverRequest) {
        return getAllGenderUseCase.execute()
                .collectList()
                .flatMap(genders -> ServerResponse.ok().bodyValue(genders))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenPutGender(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(GenderRequestDTO.class)
                .map(genderRequestDTO -> mapper.map(genderRequestDTO, GenderRequest.class))
                .flatMap(genderRequest -> updateGenderUseCase.execute(serverRequest.pathVariable("id"), genderRequest))
                .filter(Objects::nonNull)
                .flatMap(gender -> ServerResponse.ok().bodyValue(gender))
                .switchIfEmpty(Mono.defer(() -> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenSaveStatus(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(StatusRequestDTO.class)
                .map(statusRequestDTO -> mapper.map(statusRequestDTO, StatusRequest.class))
                .flatMap(saveStatusUseCase::execute)
                .flatMap(status -> ServerResponse.ok().bodyValue(status))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetAllStatus(ServerRequest serverRequest) {
        return getAllStatusUseCase.execute()
                .collectList()
                .flatMap(status -> ServerResponse.ok().bodyValue(status))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetStatusById(ServerRequest serverRequest) {
        return getStatusByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(status -> ServerResponse.ok().bodyValue(status))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenPUTStatus(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(StatusRequestDTO.class)
                .map(statusRequestDTO -> mapper.map(statusRequestDTO, StatusRequest.class))
                .flatMap(statusRequest -> updateStatusUseCase.execute(serverRequest.pathVariable("id"), statusRequest))
                .filter(Objects::nonNull)
                .flatMap(status -> ServerResponse.ok().bodyValue(status))
                .switchIfEmpty(Mono.defer(()-> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));

    }

    public Mono<ServerResponse> listenSaveDeveloper(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(DeveloperRequestDTO.class)
                .map(developerRequestDTO -> mapper.map(developerRequestDTO, DeveloperRequest.class))
                .flatMap(saveDeveloperUseCase::execute)
                .flatMap(developer -> ServerResponse.ok().bodyValue(developer))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetDeveloperById(ServerRequest serverRequest) {
        return getDeveloperByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(developer -> ServerResponse.ok().bodyValue(developer))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetAllDeveloper(ServerRequest serverRequest) {
        return getAllDeveloperUseCase.execute(serverRequest.queryParam("page").orElse("0"), serverRequest.queryParam("size").orElse("10"))
                .collectList()
                .flatMap(developers -> ServerResponse.ok().bodyValue(developers))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenPUTDeveloper(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(DeveloperRequestDTO.class)
                .map(developerRequestDTO -> mapper.map(developerRequestDTO, DeveloperRequest.class))
                .flatMap(developerRequest -> updateDeveloperUseCase.execute(serverRequest.pathVariable("id"),developerRequest))
                .filter(Objects::nonNull)
                .flatMap(developer -> ServerResponse.ok().bodyValue(developer))
                .switchIfEmpty(Mono.defer(()-> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenSaveLocation(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LocationRequestDTO.class)
                .map(locationRequestDTO -> mapper.map(locationRequestDTO, LocationRequest.class))
                .flatMap(saveLocationUseCase::execute)
                .flatMap(location -> ServerResponse.ok().bodyValue(location))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetLocationById(ServerRequest serverRequest) {
        return getLocationByIdUseCase.execute(serverRequest.pathVariable("id"))
                .flatMap(location -> ServerResponse.ok().bodyValue(location))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGetAllLocation(ServerRequest serverRequest) {
        return getAllLocationUseCase.execute()
                .collectList()
                .flatMap(location -> ServerResponse.ok().bodyValue(location))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenPUTLocation(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LocationRequestDTO.class)
                .map(locationRequestDTO -> mapper.map(locationRequestDTO, LocationRequest.class))
                .flatMap(locationRequest -> updateLocationUseCase.execute(serverRequest.pathVariable("id"), locationRequest))
                .filter(Objects::nonNull)
                .flatMap(location -> ServerResponse.ok().bodyValue(location))
                .switchIfEmpty(Mono.defer(()-> ServerResponse.notFound().build()))
                .onErrorResume(throwable -> errorMessageHanlder(throwable.getMessage()));
    }

    public Mono<ServerResponse> listenGETStatistics(ServerRequest serverRequest) {
        return statisticsUseCase.execute()
                .flatMap(statistics -> ServerResponse.ok().bodyValue(statistics));
    }

    private Mono<ServerResponse> errorMessageHanlder(Object object){
        Map<String, Object> message = new HashMap<>();
        message.put(MESSAGE, object);
        return ServerResponse.badRequest().bodyValue(message);
    }

    public Mono<ServerResponse> handleNotFound(ServerRequest serverRequest) {
        Map<String, Object> message = new HashMap<>();
        message.put(MESSAGE, PATH_NOT_FOUND);
        return ServerResponse.status(404).bodyValue(message);
    }
}
