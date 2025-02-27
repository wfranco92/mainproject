package co.com.companywf.usecase.updatevideogame;

import co.com.companywf.model.database.VideoGameDB;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.enums.Developer;
import co.com.companywf.model.enums.Gender;
import co.com.companywf.model.enums.Location;
import co.com.companywf.model.enums.Status;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.model.location.gateways.LocationRepository;
import co.com.companywf.model.status.gateway.StatusRepository;
import co.com.companywf.model.videogame.VideoGameRequest;
import co.com.companywf.model.videogame.Videogame;
import co.com.companywf.model.videogame.gateways.VideogameRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateVideoGameUseCase {

    private final VideogameRepository videogameRepository;
    private final GenderRepository genderRepository;
    private final StatusRepository statusRepository;
    private final DeveloperRepository developerRepository;
    private final LocationRepository locationRepository;

    public Mono<Videogame> execute(String id, VideoGameRequest videoGameRequest){
        return Mono.zip(genderRepository.getGenderById(Gender.idFromName(videoGameRequest.getGender().toUpperCase())),
                        statusRepository.getStatusById(Status.idFromName(videoGameRequest.getStatus().toUpperCase())),
                        developerRepository.getDeveloperById(Developer.idFromName(videoGameRequest.getDeveloper().toUpperCase())),
                        locationRepository.getLocationById(Location.idFromName(videoGameRequest.getLocation().toUpperCase())))
                .map(objects -> VideoGameDB.builder()
                        .name(videoGameRequest.getName())
                        .gender(objects.getT1())
                        .developer(objects.getT3())
                        .location(objects.getT4())
                        .status(objects.getT2())
                        .build())
                .flatMap(videoGameDB -> videogameRepository.updateVideoGame(id, videoGameDB));
    }
}
