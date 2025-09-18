package co.com.companywf.usecase.updatedeveloper;

import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateDeveloperUseCase {

    private final DeveloperRepository developerRepository;

    public Mono<Developer> execute(String id, DeveloperRequest developerRequest) {
        return Mono.just(developerRequest)
                .filter(this::validateData)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .flatMap(developValidate -> developerRepository.updateDeveloper(id, developerRequest));
    }

    private boolean validateData(DeveloperRequest developerRequest) {
        return Objects.nonNull(developerRequest.getName()) && !developerRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
