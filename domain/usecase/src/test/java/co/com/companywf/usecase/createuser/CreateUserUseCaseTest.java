package co.com.companywf.usecase.createuser;

import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.role.Role;
import co.com.companywf.model.role.gateways.RoleRepository;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passWordEncoder;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    void createUserNoAdminRoleTest(){

        UserRequest userRequest = new UserRequest("user", "12345", null, false);

        when(roleRepository.getRoleByName("ROLE_USER")).thenReturn(Mono.just(getRoleUser()));
        when(passWordEncoder.encode(any())).thenReturn("12345");
        when(userRepository.saveUser(any())).thenReturn(Mono.just(getUserNoAdminToSave()));

        StepVerifier.create(createUserUseCase.execute(userRequest))
                .expectNextMatches(user1 -> {
                    assertNotNull(user1);
                    assertEquals("12345", user1.getPassword());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test

    void createUserIsAdminRoleTest(){

        UserRequest userRequest = new UserRequest("user", "12345", null, true);

        when(roleRepository.getRoleByName("ROLE_USER")).thenReturn(Mono.just(getRoleUser()));
        when(roleRepository.getRoleByName("ROLE_ADMIN")).thenReturn(Mono.just(getRoleAdmin()));
        when(passWordEncoder.encode(any())).thenReturn("12345");
        when(userRepository.saveUser(any())).thenReturn(Mono.just(getUserIsAdminToSave()));

        StepVerifier.create(createUserUseCase.execute(userRequest))
                .expectNextMatches(user1 -> {
                    assertNotNull(user1);
                    assertEquals("12345", user1.getPassword());
                    assertTrue(user1.getRoles().contains(getRoleAdmin()));
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void validateDataTest(){

        UserRequest userRequest = new UserRequest("", "12345", null, false);

        StepVerifier.create(createUserUseCase.execute(userRequest))
                .expectError(ValidateDataException.class)
                .verify();
    }

    private User getUserNoAdminToSave() {
        return User.builder()
                .id(1L)
                .username("user")
                .password("12345")
                .roles(List.of(Role.builder()
                                .roleId(2L)
                                .rolName("ROLE_USER")
                        .build()))
                .build();
    }

    private User getUserIsAdminToSave() {
        return User.builder()
                .id(1L)
                .username("user")
                .password("12345")
                .roles(List.of(Role.builder()
                        .roleId(2L)
                        .rolName("ROLE_USER")
                        .build(),
                        Role.builder().roleId(1L).rolName("ROLE_ADMIN").build()))
                .build();
    }

    private Role getRoleAdmin() {
        return Role.builder()
                .roleId(1L)
                .rolName("ROLE_ADMIN")
                .build();
    }

    private Role getRoleUser() {
        return Role.builder()
                .roleId(2L)
                .rolName("ROLE_USER")
                .build();
    }

}