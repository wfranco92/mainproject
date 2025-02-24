package co.com.companywf.model.status.gateway;

import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatusRepository {
    Mono<Status> getStatusById(String id);
    Mono<Status> updateStatus(String id, StatusRequest statusRequest);
    Mono<Status> saveStatus(StatusRequest status);
    Flux<Status> getAllStatus();
}
