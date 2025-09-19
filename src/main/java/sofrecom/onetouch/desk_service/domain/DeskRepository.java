package sofrecom.onetouch.desk_service.domain;

import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.domain.model.Desk;

import java.util.List;
import java.util.Optional;

public interface DeskRepository {
    List<Desk> findAll();
    List<Desk> findByFloor(String floor);
    List<Desk> findByBuilding(String building);
    Optional<Desk> findById(String id);
    Desk save(Desk desk);
    void deleteById(String id);
}