package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.GenderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAGenderRepository extends CrudRepository<GenderEntity, String>, QueryByExampleExecutor<GenderEntity> {
}
