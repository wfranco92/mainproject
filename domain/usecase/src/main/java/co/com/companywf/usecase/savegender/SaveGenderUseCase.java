package co.com.companywf.usecase.savegender;

import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
import co.com.companywf.model.gender.Gender;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class SaveGenderUseCase {

    private final GenderRepository genderRepository;

    public Mono<Gender> execute(GenderRequest genderRequest){
        return Mono.just(genderRequest)
                .map(gender -> genderRequest
                        .toBuilder()
                        .genderId(UUID.randomUUID().toString())
                        .build())
                .flatMap(genderRepository::saveGender);
    }
}
