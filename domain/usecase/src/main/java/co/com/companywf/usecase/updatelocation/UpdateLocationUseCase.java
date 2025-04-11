package co.com.companywf.usecase.updatelocation;

import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateLocationUseCase {

    private final LocationRepository locationRepository;

    public Mono<Location> execute(String id, LocationRequest locationRequest){
        return Mono.just(locationRequest)
                .filter(this::validateData)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .flatMap(request -> locationRepository.updateLocation(id, locationRequest));
    }

    private boolean validateData(LocationRequest locationRequest){
        return Objects.nonNull(locationRequest) && !locationRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
