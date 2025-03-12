package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.DeveloperEntity;
import co.com.companywf.jpa.entity.UserEntity;
import co.com.companywf.jpa.helper.AdapterOperations;
import co.com.companywf.model.developer.Developer;
import co.com.companywf.model.developer.DeveloperRequest;
import co.com.companywf.model.developer.gateways.DeveloperRepository;
import co.com.companywf.model.user.User;
import co.com.companywf.model.user.UserRequest;
import co.com.companywf.model.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class JPAUserRepositoryAdapter extends AdapterOperations<User, UserEntity, Long, JPAUserRepository> implements UserRepository
{

    public JPAUserRepositoryAdapter(JPAUserRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Mono<User> getUserByName(String name) {
        return Mono.justOrEmpty(repository.findUserByUsername(name))
                .map(userEntity -> mapper.map(userEntity, User.class));
    }

    @Override
    public Mono<User> saveUser(UserRequest userRequest) {
        return Mono.justOrEmpty(userRequest)
                .map(usrRequest -> mapper.map(usrRequest, UserEntity.class))
                .map(repository::save)
                .map(usrEntity -> mapper.map(usrEntity, User.class));
    }

    @Override
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(repository.findAll())
                .map(userEntity -> mapper.map(userEntity, User.class));
    }


}
