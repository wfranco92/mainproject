package co.com.companywf.model.user.gateways;

import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> getUserByName(String name);
    Mono<User> saveUser(UserRequest userRequest);
    Flux<User> getAllUsers();


}
