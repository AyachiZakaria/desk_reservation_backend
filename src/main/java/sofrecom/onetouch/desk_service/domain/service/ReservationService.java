package sofrecom.onetouch.desk_service.domain.service;

import sofrecom.onetouch.desk_service.domain.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getReservationsByUserId(String userId);
    List<Reservation> getReservationsByDeskId(String deskId);
    List<Reservation> getReservationsByDateRange(LocalDate start, LocalDate end);
    List<Reservation> getReservationsByDeskAndDateRange(String deskId, LocalDate start, LocalDate end);
    Optional<Reservation> getReservationById(String id);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void cancelReservation(String id);
}