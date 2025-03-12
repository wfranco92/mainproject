package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface JPARoleRepository extends JpaRepository<RoleEntity, Long>, QueryByExampleExecutor<RoleEntity> {
    Optional<RoleEntity> findByRolName(String name);
}
