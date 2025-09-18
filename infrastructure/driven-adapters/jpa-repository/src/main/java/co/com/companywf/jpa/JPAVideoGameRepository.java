package co.com.companywf.jpa;

import co.com.companywf.jpa.dto.VideoGameDescriptionDTO;
import co.com.companywf.jpa.entity.VideoGameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAVideoGameRepository extends JpaRepository<VideoGameEntity, String>, QueryByExampleExecutor<VideoGameEntity> {
    @Query("SELECT new co.com.companywf.jpa.dto.VideoGameDescriptionDTO(v.id, v.name, g.name, s.description, d.name, l.name, v.createdAt) " +
            "FROM VideoGameEntity v " +
            "JOIN v.gender g " +
            "JOIN v.status s " +
            "JOIN v.developer d " +
            "JOIN v.location l")
    Page<VideoGameDescriptionDTO> findVideoGamesWhitDescription(Pageable pageable);

    @Query(value = "SELECT g.name as gender, count(v.gender_id) as amount from videogame v " +
            "JOIN gender g ON v.gender_id = g.gender_id group by v.gender_id", nativeQuery = true)
    List<Object[]> getStatisticsAboutGender();

    @Query(value = "SELECT d.name as developer, count(v.developer_id) as amount from videogame v " +
            "JOIN developer d ON v.developer_id = d.developer_id group by developer", nativeQuery = true)
    List<Object[]> getStatisticsAboutDeveloper();

    @Query(value = "select s.description as state, count(v.state_id) as amount from videogame v " +
            "JOIN state s ON v.state_id = s.state_id group by state", nativeQuery = true)
    List<Object[]> getStatisticsAboutStatus();

    @Query(value = "select l.name as location, count(v.location_id) as amount from videogame v " +
            "JOIN location l ON v.location_id = l.location_id group by location", nativeQuery = true)
    List<Object[]> getStatisticsAboutLocation();

    @Query("SELECT new co.com.companywf.jpa.dto.VideoGameDescriptionDTO(v.id, v.name, g.name, s.description, d.name, l.name, v.createdAt) " +
            "FROM VideoGameEntity v " +
            "JOIN v.gender g " +
            "JOIN v.status s " +
            "JOIN v.developer d " +
            "JOIN v.location l WHERE v.name LIKE concat('%',:name,'%')")
    List<VideoGameDescriptionDTO> findVideoGamesWhitDescriptionLikeName(@Param("name") String name);

}
