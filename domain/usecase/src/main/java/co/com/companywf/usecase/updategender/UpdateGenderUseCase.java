package co.com.companywf.usecase.updategender;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateGenderUseCase {

    private final GenderRepository genderRepository;

    public Mono<Gender> execute(String id, GenderRequest genderRequest){
        return genderRepository.updateGender(id, genderRequest);
    }
}
