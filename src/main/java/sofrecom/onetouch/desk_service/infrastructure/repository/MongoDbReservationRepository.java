package sofrecom.onetouch.desk_service.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.domain.ReservationRepository;
import sofrecom.onetouch.desk_service.domain.model.Reservation;
import sofrecom.onetouch.desk_service.infrastructure.repository.ReservationMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MongoDbReservationRepository implements ReservationRepository {
    private final SpringDataMongoReservationRepository repository;
    private final ReservationMapper reservationMapper;

    @Override
    public List<Reservation> findByUserId(String userId) {
        return repository.findByUserId(userId).stream()
                .map(reservationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByDeskId(String deskId) {
        return repository.findByDeskId(deskId).stream()
                .map(reservationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> findByStartDateBetween(LocalDate start, LocalDate end) {
        return repository.findByStartDateBetween(start, end).stream()
                .map(reservationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> findById(String id) {
        return repository.findById(id)
                .map(reservationMapper::toDomain);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationMapper.toDomain(
                repository.save(reservationMapper.toEntity(reservation))
        );
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}