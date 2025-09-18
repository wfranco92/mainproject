package co.com.companywf.usecase.updatestatus;


import co.com.companywf.model.enums.MessageUtilsEnum;
import co.com.companywf.model.exception.ValidateDataException;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateStatusUseCase {

    private final StatusRepository statusRepository;

    public Mono<Status> execute(String id, StatusRequest statusRequest){
        return Mono.just(statusRequest)
                .filter(this::validateData)
                .switchIfEmpty(Mono.error(new ValidateDataException(MessageUtilsEnum.NOT_VALIDATE_BODY_DATA.getMessage())))
                .flatMap(request -> statusRepository.updateStatus(id, statusRequest));
    }

    private boolean validateData(StatusRequest statusRequest) {
        return Objects.nonNull(statusRequest.getDescription()) && !statusRequest.getDescription().equals(MessageUtilsEnum.EMPTY.getMessage());
    }
}
