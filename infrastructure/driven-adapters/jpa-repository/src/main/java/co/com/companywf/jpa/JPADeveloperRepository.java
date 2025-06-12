package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPADeveloperRepository extends JpaRepository<DeveloperEntity, String>, QueryByExampleExecutor<DeveloperEntity> {
}
