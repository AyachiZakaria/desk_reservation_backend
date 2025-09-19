package sofrecom.onetouch.desk_service.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.infrastructure.api.dto.DeskEntity;

import java.util.List;

@Repository
public interface SpringDataMongoDeskRepository extends MongoRepository<DeskEntity, String> {
    List<DeskEntity> findByLocationFloor(String floor);
    List<DeskEntity> findByLocationBuilding(String building);
}
