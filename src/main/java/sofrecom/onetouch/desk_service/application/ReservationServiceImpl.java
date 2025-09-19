package sofrecom.onetouch.desk_service.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sofrecom.onetouch.desk_service.domain.DeskRepository;
import sofrecom.onetouch.desk_service.domain.ReservationRepository;
import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.domain.model.Reservation;
import sofrecom.onetouch.desk_service.domain.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final DeskRepository deskRepository;

    @Override
    public List<Reservation> getReservationsByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> getReservationsByDeskId(String deskId) {
        return reservationRepository.findByDeskId(deskId);
    }

    @Override
    public List<Reservation> getReservationsByDateRange(LocalDate start, LocalDate end) {
        return reservationRepository.findByStartDateBetween(start, end);
    }

    @Override
    public List<Reservation> getReservationsByDeskAndDateRange(String deskId, LocalDate start, LocalDate end) {
        return reservationRepository.findByDeskId(deskId).stream()
                .filter(reservation ->
                        !reservation.getStartDate().isAfter(end) &&
                                !reservation.getEndDate().isBefore(start))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        // Check if desk exists and is available
        Optional<Desk> desk = deskRepository.findById(reservation.getDeskId());
        if (desk.isEmpty() || !desk.get().isAvailable() || desk.get().getStatus() != Desk.DeskStatus.AVAILABLE) {
            throw new IllegalStateException("Desk is not available for reservation");
        }

        // Check for overlapping reservations
        List<Reservation> existingReservations = reservationRepository.findByDeskId(reservation.getDeskId());
        boolean hasOverlap = existingReservations.stream()
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED)
                .anyMatch(r ->
                        !reservation.getStartDate().isAfter(r.getEndDate()) &&
                                !reservation.getEndDate().isBefore(r.getStartDate()));

        if (hasOverlap) {
            throw new IllegalStateException("There is an overlapping reservation for this desk");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        // Similar validation as in create
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(String id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Reservation r = reservation.get();
            r.setStatus(Reservation.ReservationStatus.CANCELLED);
            reservationRepository.save(r);
        }
    }
}
