package co.com.companywf.usecase.saveallvideogames;

import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.enums.Developer;
import co.com.companywf.model.enums.Gender;
import co.com.companywf.model.enums.Location;
import co.com.companywf.model.enums.Status;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.model.status.gateway.StatusRepository;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveAllVideoGamesUseCase {

    private final VideogameRepository videogameRepository;
    private final GenderRepository genderRepository;
    private final StatusRepository statusRepository;
    private final DeveloperRepository developerRepository;
    private final LocationRepository locationRepository;

    public Flux<Videogame> execute(List<VideoGameRequest> videoGameRequest){
        return Flux.fromIterable(videoGameRequest)
                .flatMap(videoGame -> Mono.zip(genderRepository.getGenderById(Gender.idFromName(videoGame.getGender().toUpperCase())),
                        statusRepository.getStatusById(Status.idFromName(videoGame.getStatus().toUpperCase())),
                        developerRepository.getDeveloperById(Developer.idFromName(videoGame.getDeveloper().toUpperCase())),
                        locationRepository.getLocationById(Location.idFromName(videoGame.getLocation().toUpperCase())))
                        .map(objects -> VideoGameDB.builder()
                                .videogameId(UUID.randomUUID().toString())
                                .name(videoGame.getName())
                                .gender(objects.getT1())
                                .developer(objects.getT3())
                                .location(objects.getT4())
                                .status(objects.getT2())
                                .build()))
                .collectList()
                .flatMapMany(videogameRepository::saveAllVideoGames);
    }
}
