package co.com.companywf.usecase.finduser;

import co.com.companywf.model.role.Role;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FindUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

   @InjectMocks
   private FindUserUseCase findUserUseCase;

    @Test
    void getUserByNameTest(){
        User user = new User(1L, "user", "user", List.of(new Role(1L, "USER")));
        UserRequest userRequest = new UserRequest("user", "user", List.of(new Role(1L, "USER")), false);
        when(userRepository.getUserByName(any())).thenReturn(Mono.just(user));

        StepVerifier.create(findUserUseCase.execute(userRequest))
                .expectNext(user)
                .verifyComplete();

        verify(userRepository, times(1)).getUserByName("user");
    }

}