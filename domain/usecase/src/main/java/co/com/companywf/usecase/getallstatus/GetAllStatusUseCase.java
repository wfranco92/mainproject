package co.com.companywf.usecase.getallstatus;

import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.gateway.StatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllStatusUseCase {

    private final StatusRepository statusRepository;

    public Flux<Status> execute(){
        return statusRepository.getAllStatus();
    }
}
