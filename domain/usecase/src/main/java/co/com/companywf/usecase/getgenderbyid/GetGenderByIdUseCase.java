package co.com.companywf.usecase.getgenderbyid;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.gateway.GenderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetGenderByIdUseCase {

    private final GenderRepository genderRepository;

    public Mono<Gender> execute (String id){
        return genderRepository.getGenderById(id);
    }
}
