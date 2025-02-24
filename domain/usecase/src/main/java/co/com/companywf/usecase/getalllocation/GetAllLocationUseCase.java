package co.com.companywf.usecase.getalllocation;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllLocationUseCase {

    private final LocationRepository locationRepository;

    public Flux<Location> execute(){
        return locationRepository.getAllLocation();
    }
}
