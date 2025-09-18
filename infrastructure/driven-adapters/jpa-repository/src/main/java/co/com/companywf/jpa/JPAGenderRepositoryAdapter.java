package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.GenderEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.gender.Gender;
import co.com.companywf.model.gender.GenderRequest;
import co.com.companywf.model.gender.gateway.GenderRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class JPAGenderRepositoryAdapter extends AdapterOperations<Gender, GenderEntity, String, JPAGenderRepository> implements GenderRepository
{

    public JPAGenderRepositoryAdapter(JPAGenderRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Gender.class));
    }

    @Override
    @Cacheable("gender")
    public Mono<Gender> getGenderById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(genderEntity -> mapper.map(genderEntity, Gender.class)));
    }

    @Override
    public Mono<Gender> saveGender(GenderRequest genderRequest) {
        return Mono.justOrEmpty(genderRequest)
                .map(request -> mapper.map(request, GenderEntity.class))
                .map(repository::save)
                .map(genderEntity -> mapper.map(genderEntity, Gender.class));
    }

    @Override
    public Mono<Gender> updateGender(String id, GenderRequest genderRequest) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(genderEntity -> genderEntity.toBuilder()
                        .name(genderRequest.getName()).build())
                .map(repository::save)
                .map(genderEntity -> mapper.map(genderEntity, Gender.class));
    }

    @Override
    public Flux<Gender> getAllGender() {
        return Flux.fromIterable(repository.findAll())
                .map(genderEntity -> mapper.map(genderEntity, Gender.class));
    }
}
