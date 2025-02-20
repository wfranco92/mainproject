package co.com.companywf.usecase.getlocationbyid;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetLocationByIdUseCase {

    private final LocationRepository locationRepository;

    public Mono<Location> execute(String id){
        return locationRepository.getLocationById(id);
    }
}
