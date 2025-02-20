package co.com.companywf.model.developer.gateways;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeveloperRepository {
    Mono<Developer> getDeveloperById(String id);
    Flux<Developer> getAllDeveloper();
    Mono<Developer> saveDeveloper(DeveloperRequest developerRequest);

}
