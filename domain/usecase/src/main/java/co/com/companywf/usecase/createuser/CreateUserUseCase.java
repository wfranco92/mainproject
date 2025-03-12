package co.com.companywf.usecase.createuser;

import co.com.companywf.model.role.gateways.RoleRepository;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passWordEncoder;
    private final RoleRepository roleRepository;

    public Mono<User> execute(UserRequest userRequest) {
        return Mono.just(userRequest)
                .flatMap(request -> roleRepository.getRoleByName("ROLE_USER")
                        .flatMap(role -> Mono.just(userRequest)
                                .filter(req -> userRequest.isAdmin())
                                .flatMap(userFiltered -> roleRepository.getRoleByName("ROLE_ADMIN"))
                                .map(role2 -> userRequest.toBuilder().roles(List.of(role, role2)).build())
                                .switchIfEmpty(Mono.defer(() -> Mono.just(userRequest.toBuilder().roles(List.of(role)).build()))))
                        .map(requestBuild -> requestBuild.toBuilder()
                                .password(passWordEncoder.encode(userRequest.getPassword()))
                                .build())
                        .flatMap(userRepository::saveUser)
                );
    }
}
