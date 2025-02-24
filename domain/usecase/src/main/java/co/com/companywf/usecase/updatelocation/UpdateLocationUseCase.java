package co.com.companywf.usecase.updatelocation;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateLocationUseCase {

    private final LocationRepository locationRepository;

    public Mono<Location> execute(String id, LocationRequest locationRequest){
        return locationRepository.updateLocation(id, locationRequest);
    }
}
