package co.com.companywf.usecase.savedeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class SaveDeveloperUseCase {

    private final DeveloperRepository developerRepository;

    public Mono<Developer> execute(DeveloperRequest developerRequest){
        return Mono.just(developerRequest)
                .map(request -> developerRequest.toBuilder()
                        .developerId(UUID.randomUUID().toString())
                        .build())
                .flatMap(developerRepository::saveDeveloper);
    }
}
