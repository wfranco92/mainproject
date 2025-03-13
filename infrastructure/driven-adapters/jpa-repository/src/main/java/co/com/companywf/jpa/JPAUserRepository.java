package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAUserRepository extends JpaRepository<UserEntity, Long>, QueryByExampleExecutor<UserEntity> {
    UserEntity findUserByUsername(String username);
}
