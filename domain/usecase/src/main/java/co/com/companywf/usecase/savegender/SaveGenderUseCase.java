package co.com.companywf.usecase.savegender;

import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
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

    public Mono<Gender> execute(GenderRequest genderRequest){
        return Mono.just(genderRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .map(gender -> genderRequest
                        .toBuilder()
                        .genderId(UUID.randomUUID().toString())
                        .build())
                .flatMap(genderRepository::saveGender);
    }

    @Override
    public boolean validateBody(GenderRequest genderRequest) {
        return Objects.nonNull(genderRequest) && !genderRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
