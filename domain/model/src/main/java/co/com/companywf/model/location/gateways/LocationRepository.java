package co.com.companywf.model.location.gateways;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LocationRepository {
    Mono<Location> getLocationById(String id);
    Mono<Location> saveLocation(LocationRequest locationRequest);
    Mono<Location> updateLocation(String id, LocationRequest locationRequest);
    Flux<Location> getAllLocation();

}
