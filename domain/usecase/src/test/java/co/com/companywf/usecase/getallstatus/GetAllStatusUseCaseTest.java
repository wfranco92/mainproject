package co.com.companywf.usecase.getallstatus;

import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.gateway.StatusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllStatusUseCaseTest {

    @Mock
    private StatusRepository repository;

    @InjectMocks
    private GetAllStatusUseCase getAllStatusUseCase;

    @Test
    void getStatus(){

        when(repository.getAllStatus()).thenReturn(Flux.fromIterable(getStatusList()));

        StepVerifier.create(getAllStatusUseCase.execute())
                .expectNextCount(2)
                .expectComplete()
                .verify();
    }

    private List<Status> getStatusList(){
        return List.of(Status.builder().build(),
                Status.builder().build());
    }
}