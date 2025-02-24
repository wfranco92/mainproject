package co.com.companywf.usecase.savestatus;

import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class SaveStatusUseCase {

    private final StatusRepository statusRepository;

    public Mono<Status> execute(StatusRequest status){
        return Mono.just(status)
                .map(statusRequest -> status.toBuilder()
                        .statusId(UUID.randomUUID().toString())
                        .build())
                .flatMap(statusRepository::saveStatus);
    }
}
