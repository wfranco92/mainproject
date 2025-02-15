package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.videogame.Location;
import reactor.core.publisher.Mono;

public interface LocationRepository {
    Mono<Location> getLocationById(String id);

}
