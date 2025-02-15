package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.StatusEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.videogame.Status;
import co.com.companywf.model.videogame.gateways.StatusRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
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
    public Mono<Status> getStatusById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(statusEntity -> mapper.map(statusEntity, Status.class)));
    }
}
