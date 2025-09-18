package co.com.companywf.usecase.savestatus;

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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveStatusUseCaseTest {

    @Mock
    private StatusRepository repository;

    @InjectMocks
    private SaveStatusUseCase useCase;

    @Test
    void saveStatusTest(){

        StatusRequest request = StatusRequest.builder().description("status").build();
        Status status = Status.builder().statusId("ID").description("status").createdAt(LocalDateTime.now()).build();

        when(repository.saveStatus(any())).thenReturn(Mono.just(status));

        StepVerifier.create(useCase.execute(request))
                .expectNext(status)
                .verifyComplete();
    }

    @Test
    void validateData(){
        StatusRequest request = new StatusRequest();
        StepVerifier.create(useCase.execute(request))
                .expectError(ValidateDataException.class)
                .verify();
    }
}