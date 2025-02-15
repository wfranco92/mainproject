package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.videogame.Status;
import reactor.core.publisher.Mono;

public interface StatusRepository {
    Mono<Status> getStatusById(String id);

}
