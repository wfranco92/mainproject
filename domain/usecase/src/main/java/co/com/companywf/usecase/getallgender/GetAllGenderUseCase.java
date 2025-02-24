package co.com.companywf.usecase.getallgender;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.gateway.GenderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllGenderUseCase {
    private final GenderRepository genderRepository;

    public Flux<Gender> execute(){
        return genderRepository.getAllGender();
    }
}
