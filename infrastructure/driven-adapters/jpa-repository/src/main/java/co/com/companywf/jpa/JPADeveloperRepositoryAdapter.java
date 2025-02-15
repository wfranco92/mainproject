package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.DeveloperEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.videogame.Developer;
import co.com.companywf.model.videogame.gateways.DeveloperRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPADeveloperRepositoryAdapter extends AdapterOperations<Developer, DeveloperEntity, String, JPADeveloperRepository> implements DeveloperRepository
{

    public JPADeveloperRepositoryAdapter(JPADeveloperRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Developer.class));
    }

    @Override
    public Mono<Developer> getDeveloperById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(developer -> mapper.map(developer, Developer.class)));
    }
}
