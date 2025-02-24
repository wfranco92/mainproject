package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.DeveloperEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPADeveloperRepository extends CrudRepository<DeveloperEntity, String>, QueryByExampleExecutor<DeveloperEntity> {
}
