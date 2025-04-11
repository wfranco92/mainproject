package co.com.companywf.usecase.updategender;

import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateGenderUseCase {

    private final GenderRepository genderRepository;

    public Mono<Gender> execute(String id, GenderRequest genderRequest){
        return Mono.just(genderRequest)
                .filter(this::validateData)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .flatMap(request -> genderRepository.updateGender(id, genderRequest));
    }

    private boolean validateData(GenderRequest genderRequest) {
        return Objects.nonNull(genderRequest.getName()) && !genderRequest.getName().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
