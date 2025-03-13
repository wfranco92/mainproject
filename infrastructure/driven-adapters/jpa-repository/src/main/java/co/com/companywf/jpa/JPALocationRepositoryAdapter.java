package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.LocationEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.location.Location;
import co.com.companywf.model.location.LocationRequest;
import co.com.companywf.model.location.gateways.LocationRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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
    @Cacheable("location")
    public Mono<Location> getLocationById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(locationEntity -> mapper.map(locationEntity, Location.class)));
    }

    @Override
    public Mono<Location> saveLocation(LocationRequest locationRequest) {
        return Mono.justOrEmpty(locationRequest)
                .map(locationRequest1 -> mapper.map(locationRequest1, LocationEntity.class))
                .map(repository::save)
                .map(locationEntity -> mapper.map(locationEntity, Location.class));
    }

    @Override
    public Mono<Location> updateLocation(String id, LocationRequest locationRequest) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(locationEntity -> locationEntity.toBuilder().name(locationRequest.getName()).build())
                .map(repository::save)
                .map(locationEntity -> mapper.map(locationEntity, Location.class));
    }

    @Override
    public Flux<Location> getAllLocation() {
        return Flux.fromIterable(repository.findAll())
                .map(locationEntity -> mapper.map(locationEntity, Location.class));
    }
}
