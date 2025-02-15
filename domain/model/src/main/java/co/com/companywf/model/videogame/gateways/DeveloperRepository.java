package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.videogame.Developer;
import reactor.core.publisher.Mono;

public interface DeveloperRepository {
    Mono<Developer> getDeveloperById(String id);

}
