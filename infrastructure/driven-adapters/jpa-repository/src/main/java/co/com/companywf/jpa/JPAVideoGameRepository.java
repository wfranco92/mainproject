package co.com.companywf.jpa;

import co.com.companywf.jpa.dto.VideoGameDescriptionDTO;
import co.com.companywf.jpa.entity.VideoGameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAVideoGameRepository extends JpaRepository<VideoGameEntity, String>, QueryByExampleExecutor<VideoGameEntity> {
    @Query("SELECT new co.com.companywf.jpa.dto.VideoGameDescriptionDTO(v.videogameId, v.name, g.name, s.description, d.name, l.name, v.createdAt) " +
            "FROM VideoGameEntity v " +
            "JOIN v.gender g " +
            "JOIN v.status s " +
            "JOIN v.developer d " +
            "JOIN v.location l")
    Page<VideoGameDescriptionDTO> findVideoGamesWhitDescription(Pageable pageable);
}
