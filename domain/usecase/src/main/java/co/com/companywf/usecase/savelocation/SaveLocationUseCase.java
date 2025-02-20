package co.com.companywf.usecase.savelocation;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class SaveLocationUseCase {

    private final LocationRepository locationRepository;

    public Mono<Location> execute(LocationRequest locationRequest){
        return Mono.just(locationRequest)
                .map(request -> locationRequest.toBuilder()
                        .locationId(UUID.randomUUID().toString())
                        .build())
                .flatMap(locationRepository::saveLocation);
    }
}
