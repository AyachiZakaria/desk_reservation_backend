package sofrecom.onetouch.desk_service.domain;

import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.domain.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByDeskId(String deskId);
    List<Reservation> findByStartDateBetween(LocalDate start, LocalDate end);
    Optional<Reservation> findById(String id);
    Reservation save(Reservation reservation);
    void deleteById(String id);
}