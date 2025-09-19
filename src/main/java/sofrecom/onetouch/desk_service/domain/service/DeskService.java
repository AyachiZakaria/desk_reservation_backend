package sofrecom.onetouch.desk_service.domain.service;

import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.domain.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface DeskService {
    List<Desk> getAllDesks();

    List<Desk> getDesksByFloor(String floor);

    List<Desk> getDesksByBuilding(String building);

    Optional<Desk> getDeskById(String id);

    Desk createDesk(Desk desk);

    Desk updateDesk(Desk desk);

    void deleteDesk(String id);
}