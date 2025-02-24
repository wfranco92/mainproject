package co.com.companywf.usecase.updatestatus;


import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateStatusUseCase {

    private final StatusRepository statusRepository;

    public Mono<Status> execute(String id, StatusRequest statusRequest){
        return statusRepository.updateStatus(id, statusRequest);
    }
}
