package co.com.companywf.usecase.getstatusbyid;

import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.gateway.StatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetStatusByIdUseCase {

    private final StatusRepository statusRepository;

    public Mono<Status> execute(String id){
        return statusRepository.getStatusById(id);
    }
}
