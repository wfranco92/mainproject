package co.com.companywf.usecase.savestatus;

import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import co.com.companywf.usecase.AbstractUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveStatusUseCase extends AbstractUseCase<StatusRequest> {

    private final StatusRepository statusRepository;
    private static final String EMPTY = "";
    private static final String NOT_VALIDATE_BODY_DATA = "los campos del body request no cumplen con la validacion minima.";


    public Mono<Status> execute(StatusRequest status){
        return Mono.just(status)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new RuntimeException(NOT_VALIDATE_BODY_DATA)))
                .map(statusRequest -> status.toBuilder()
                        .statusId(UUID.randomUUID().toString())
                        .build())
                .flatMap(statusRepository::saveStatus);
    }

    @Override
    public boolean validateBody(StatusRequest statusRequest) {
        return Objects.nonNull(statusRequest.getDescription()) && !statusRequest.getDescription().equals(EMPTY);
    }
}
