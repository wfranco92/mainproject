package co.com.companywf.jpa;

import co.com.companywf.jpa.entity.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPALocationRepository extends CrudRepository<LocationEntity, String>, QueryByExampleExecutor<LocationEntity> {
}
