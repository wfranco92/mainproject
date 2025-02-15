package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.LocationEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.videogame.Location;
import co.com.companywf.model.videogame.gateways.LocationRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPALocationRepositoryAdapter extends AdapterOperations<Location, LocationEntity, String, JPALocationRepository> implements LocationRepository
{

    public JPALocationRepositoryAdapter(JPALocationRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Location.class));
    }

    @Override
    public Mono<Location> getLocationById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(locationEntity -> mapper.map(locationEntity, Location.class)));
    }
}
