package co.com.companywf.usecase.createuser;

import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.role.gateways.RoleRepository;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import co.com.companywf.usecase.AbstractUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CreateUserUseCase extends AbstractUseCase<UserRequest> {

    private final UserRepository userRepository;
    private final PasswordEncoder passWordEncoder;
    private final RoleRepository roleRepository;

    private static final String EMPTY = "";
    private static final String NOT_VALIDATE_BODY_DATA = "los campos del body request no cumplen con la validacion minima.";


    public Mono<User> execute(UserRequest userRequest) {
        return Mono.just(userRequest)
                .filter(this::validateBody)
                .switchIfEmpty(Mono.error(new ValidateDataException(NOT_VALIDATE_BODY_DATA)))
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

    @Override
    public boolean validateBody(UserRequest userRequest) {
        return Objects.nonNull(userRequest.getUsername()) && !userRequest.getUsername().equals(EMPTY) &&
                Objects.nonNull(userRequest.getPassword()) && !userRequest.getPassword().equals(EMPTY);
    }
}
