package co.com.companywf.usecase.updatedeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateDeveloperUseCase {

    private final DeveloperRepository developerRepository;

    public Mono<Developer> execute(String id, DeveloperRequest developerRequest){
        return developerRepository.updateDeveloper(id, developerRequest);
    }
}
