package co.com.companywf.usecase.updatestatus;

import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStatusUseCaseTest {


    @Mock
    private StatusRepository repository;

    @InjectMocks
    private UpdateStatusUseCase useCase;

    @Test
    void updateStateTest(){

        StatusRequest request = StatusRequest.builder().description("updated").build();

        when(repository.updateStatus(any(), any())).thenReturn(Mono.just(Status.builder().statusId("123").description("updated").build()));

        StepVerifier.create(useCase.execute("123", request))
                .expectNextMatches(status -> {
                    assertEquals("123", status.getStatusId());
                    assertEquals(request.getDescription(), status.getDescription());
                    return true;
                }).expectComplete().verify();
    }

    @Test
    void validateDataTest(){
        StepVerifier.create(useCase.execute("123", StatusRequest.builder().build()))
                .expectError(ValidateDataException.class);
    }

}