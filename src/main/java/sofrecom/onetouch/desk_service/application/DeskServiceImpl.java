package sofrecom.onetouch.desk_service.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sofrecom.onetouch.desk_service.domain.DeskRepository;
import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.domain.service.DeskService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {
    private final DeskRepository deskRepository;

    @Override
    public List<Desk> getAllDesks() {
        return deskRepository.findAll();
    }

    @Override
    public List<Desk> getDesksByFloor(String floor) {
        return deskRepository.findByFloor(floor);
    }

    @Override
    public List<Desk> getDesksByBuilding(String building) {
        return deskRepository.findByBuilding(building);
    }

    @Override
    public Optional<Desk> getDeskById(String id) {
        return deskRepository.findById(id);
    }

    @Override
    public Desk createDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    @Override
    public Desk updateDesk(Desk desk) {
        return deskRepository.save(desk);
    }

    @Override
    public void deleteDesk(String id) {
        deskRepository.deleteById(id);
    }
}