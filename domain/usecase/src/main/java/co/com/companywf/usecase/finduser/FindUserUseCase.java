package co.com.companywf.usecase.finduser;

import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(UserRequest userRequest){
        return userRepository.getUserByName(userRequest.getUsername());
    }
}
