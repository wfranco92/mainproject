package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.RoleEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.role.Role;
import co.com.companywf.model.role.gateways.RoleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class JPARoleRepositoryAdapter extends AdapterOperations<Role, RoleEntity, Long, JPARoleRepository> implements RoleRepository
{

    public JPARoleRepositoryAdapter(JPARoleRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Role.class));
    }

    @Override
    public Mono<Role> getRoleByName(String name) {
        return Mono.justOrEmpty(repository.findByRolName(name))
                .map(role -> mapper.map(role, Role.class));
    }
}
