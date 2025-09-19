package sofrecom.onetouch.desk_service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.domain.DeskRepository;
import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.infrastructure.repository.DeskMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MongoDbDeskRepository implements DeskRepository {
    private final SpringDataMongoDeskRepository repository;
    private final DeskMapper deskMapper;

    @Override
    public List<Desk> findAll() {
        return repository.findAll().stream()
                .map(deskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Desk> findByFloor(String floor) {
        return repository.findByLocationFloor(floor).stream()
                .map(deskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Desk> findByBuilding(String building) {
        return repository.findByLocationBuilding(building).stream()
                .map(deskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Desk> findById(String id) {
        return repository.findById(id)
                .map(deskMapper::toDomain);
    }

    @Override
    public Desk save(Desk desk) {
        return deskMapper.toDomain(
                repository.save(deskMapper.toEntity(desk))
        );
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}