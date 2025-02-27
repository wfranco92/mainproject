package co.com.companywf.usecase.savedeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.usecase.AbstractUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveDeveloperUseCase extends AbstractUseCase<DeveloperRequest> {

    private final DeveloperRepository developerRepository;
    private static final String EMPTY = "";
    private static final String NOT_VALIDATE_BODY_DATA = "los campos del body request no cumplen con la validacion minima.";

    public Mono<Developer> execute(DeveloperRequest developerRequest){
        return Mono.just(developerRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new RuntimeException(NOT_VALIDATE_BODY_DATA)))
                .map(request -> developerRequest.toBuilder()
                        .developerId(UUID.randomUUID().toString())
                        .build())
                .flatMap(developerRepository::saveDeveloper);
    }

    @Override
    public boolean validateBody(DeveloperRequest developerRequest) {
        return Objects.nonNull(developerRequest.getName()) && !developerRequest.getName().equals(EMPTY);
    }
}
