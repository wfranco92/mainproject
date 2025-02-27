package co.com.companywf.usecase.savelocation;

import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import co.com.companywf.usecase.AbstractUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveLocationUseCase extends AbstractUseCase<LocationRequest> {

    private final LocationRepository locationRepository;
    private static final String EMPTY = "";
    private static final String NOT_VALIDATE_BODY_DATA = "los campos del body request no cumplen con la validacion minima.";

    public Mono<Location> execute(LocationRequest locationRequest){
        return Mono.just(locationRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new RuntimeException(NOT_VALIDATE_BODY_DATA)))
                .map(request -> locationRequest.toBuilder()
                        .locationId(UUID.randomUUID().toString())
                        .build())
                .flatMap(locationRepository::saveLocation);
    }

    @Override
    public boolean validateBody(LocationRequest locationRequest) {
        return Objects.nonNull(locationRequest.getName()) && !locationRequest.getName().equals(EMPTY);
    }
}
