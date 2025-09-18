package co.com.companywf.usecase.getstatusbyid;

import co.com.companywf.model.status.Status;
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
class GetStatusByIdUseCaseTest {

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private GetStatusByIdUseCase getStatusByIdUseCase;

    @Test
    void getStatusById(){

        Status status = new Status("POI", "status", LocalDateTime.now());

        when(statusRepository.getStatusById(any())).thenReturn(Mono.just(getStatus()));

        StepVerifier.create(getStatusByIdUseCase.execute(any()))
                .expectNext(status);
    }

    private Status getStatus(){
        return Status.builder()
                .statusId("POI")
                .description("status")
                .createdAt(LocalDateTime.now())
                .build();
    }
}