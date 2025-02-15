package co.com.companywf.model.videogame.gateways;

import co.com.companywf.model.videogame.Gender;
import reactor.core.publisher.Mono;

public interface GenderRepository {
    Mono<Gender> getGenderById(String id);

}
