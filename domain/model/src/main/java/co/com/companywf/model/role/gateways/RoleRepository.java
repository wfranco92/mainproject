package co.com.companywf.model.role.gateways;

import co.com.companywf.model.role.Role;
import reactor.core.publisher.Mono;

public interface RoleRepository {
    Mono<Role> getRoleByName(String name);
}
