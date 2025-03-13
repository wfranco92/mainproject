package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.StatusEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.status.Status;
import co.com.companywf.model.status.StatusRequest;
import co.com.companywf.model.status.gateway.StatusRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class JPAStatusRepositoryAdapter extends AdapterOperations<Status, StatusEntity, String, JPAStatusRepository> implements StatusRepository
{

    public JPAStatusRepositoryAdapter(JPAStatusRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Status.class));
    }

    @Override
    @Cacheable("status")
    public Mono<Status> getStatusById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(statusEntity -> mapper.map(statusEntity, Status.class)));
    }

    @Override
    public Mono<Status> updateStatus(String id, StatusRequest statusRequest) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(statusEntity -> statusEntity.toBuilder().description(statusRequest.getDescription()).build())
                .map(repository::save)
                .map(statusEntity -> mapper.map(statusEntity, Status.class));
    }

    @Override
    public Mono<Status> saveStatus(StatusRequest request) {
        return Mono.justOrEmpty(request)
                .map(request1 -> mapper.map(request1, StatusEntity.class))
                .map(repository::save)
                .map(statusEntity -> mapper.map(statusEntity, Status.class));
    }

    @Override
    public Flux<Status> getAllStatus() {
        return Flux.fromIterable(repository.findAll())
                .map(statusEntity -> mapper.map(statusEntity, Status.class));
    }
}
