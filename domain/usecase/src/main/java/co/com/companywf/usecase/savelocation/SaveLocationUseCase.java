package co.com.companywf.usecase.savelocation;

import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
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

    public Mono<Location> execute(LocationRequest locationRequest){
        return Mono.just(locationRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .map(request -> locationRequest.toBuilder()
                        .locationId(UUID.randomUUID().toString())
                        .build())
                .flatMap(locationRepository::saveLocation);
    }

    @Override
    public boolean validateBody(LocationRequest locationRequest) {
        return Objects.nonNull(locationRequest.getName()) && !locationRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
