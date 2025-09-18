package co.com.companywf.usecase.getalluser;

import co.com.companywf.model.user.User;
import co.com.companywf.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllUserUseCase {

    private final UserRepository userRepository;

    public Flux<User> execute(){
        return userRepository.getAllUsers();
    }
}
