package co.com.companywf.usecase.savegender;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.usecase.AbstractUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class SaveGenderUseCase extends AbstractUseCase<GenderRequest> {

    private final GenderRepository genderRepository;
    private static final String EMPTY = "";
    private static final String NOT_VALIDATE_BODY_DATA = "los campos del body request no cumplen con la validacion minima.";


    public Mono<Gender> execute(GenderRequest genderRequest){
        return Mono.just(genderRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new RuntimeException(NOT_VALIDATE_BODY_DATA)))
                .map(gender -> genderRequest
                        .toBuilder()
                        .genderId(UUID.randomUUID().toString())
                        .build())
                .flatMap(genderRepository::saveGender);
    }

    @Override
    public boolean validateBody(GenderRequest genderRequest) {
        return Objects.nonNull(genderRequest) && !genderRequest.getName().equals(EMPTY);
    }
}
