package co.com.companywf.usecase.saveallvideogames;

import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.enums.*;
import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.model.location.gateways.LocationRepository;
import co.com.companywf.model.status.gateway.StatusRepository;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import co.com.companywf.usecase.AbstractUseCase;
import co.com.companywf.utils.Parser;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveAllVideoGamesUseCase extends AbstractUseCase<VideoGameRequest> {

    private final VideogameRepository videogameRepository;
    private final GenderRepository genderRepository;
    private final StatusRepository statusRepository;
    private final DeveloperRepository developerRepository;
    private final LocationRepository locationRepository;

    public Flux<Videogame> execute(List<VideoGameRequest> videoGameRequest){
        return Flux.fromIterable(videoGameRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .flatMap(videoGame -> Mono.zip(genderRepository.getGenderById(Gender.idFromName(videoGame.getGender().toUpperCase())),
                        statusRepository.getStatusById(Status.idFromName(videoGame.getStatus().toUpperCase())),
                        developerRepository.getDeveloperById(Developer.idFromName(videoGame.getDeveloper().toUpperCase())),
                        locationRepository.getLocationById(Location.idFromName(videoGame.getLocation().toUpperCase())))
                        .map(objects -> VideoGameDB.builder()
                                .id(UUID.randomUUID().toString())
                                .name(videoGame.getName())
                                .gender(objects.getT1())
                                .developer(objects.getT3())
                                .location(objects.getT4())
                                .status(objects.getT2())
                                .build()))
                .collectList()
                .flatMapMany(videogameRepository::saveAllVideoGames)
                .flatMap(Parser::getVideogameFormater);
    }

    public boolean validateBody(VideoGameRequest videoGameRequest){
        return Objects.nonNull(videoGameRequest) &&
                Objects.nonNull(videoGameRequest.getName()) && !videoGameRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage()) &&
                Objects.nonNull(videoGameRequest.getDeveloper()) && !videoGameRequest.getDeveloper().equals(MessageUtilsEnum.EMPTY.getMessage()) &&
                Objects.nonNull(videoGameRequest.getGender()) && !videoGameRequest.getGender().equals(MessageUtilsEnum.EMPTY.getMessage()) &&
                Objects.nonNull(videoGameRequest.getStatus()) && !videoGameRequest.getStatus().equals(MessageUtilsEnum.EMPTY.getMessage()) &&
                Objects.nonNull(videoGameRequest.getLocation()) && !videoGameRequest.getLocation().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
