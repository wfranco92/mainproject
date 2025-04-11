package co.com.companywf.usecase.getalluser;

import co.com.companywf.model.role.Role;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.gateways.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GetAllUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllUserUseCase GetAllUserUseCase;

@Test
    void getAllUsers(){
        User user1 = new User(1L, "user", "user", List.of(new Role(1L, "USER")));
        User user2 = new User(2L, "user2", "user2", List.of(new Role(1L, "USER")));

    when(userRepository.getAllUsers()).thenReturn(Flux.just(user1, user2));

        StepVerifier.create(GetAllUserUseCase.execute())
                .expectNext(user1)
                .expectNext(user2)
                .verifyComplete();

        verify(userRepository, times(1)).getAllUsers();
    }
}