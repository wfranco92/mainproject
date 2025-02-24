package co.com.companywf.model.gender.gateway;

import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.Gender;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenderRepository {
    Mono<Gender> getGenderById(String id);
    Mono<Gender> saveGender(GenderRequest genderRequest);
    Mono<Gender> updateGender(String id, GenderRequest genderRequest);
    Flux<Gender> getAllGender();
}
