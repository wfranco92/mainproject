package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.GenderEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.videogame.Gender;
import co.com.companywf.model.videogame.gateways.GenderRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
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
    public Mono<Gender> getGenderById(String id) {
        return Mono.justOrEmpty(repository.findById(id)
                .map(genderEntity -> mapper.map(genderEntity, Gender.class)));
    }
}
