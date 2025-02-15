package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAStatusRepository extends CrudRepository<StatusEntity, String>, QueryByExampleExecutor<StatusEntity> {
}
