package sofrecom.onetouch.desk_service.infrastructure.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.domain.model.Reservation;
import sofrecom.onetouch.desk_service.domain.service.DeskService;
import sofrecom.onetouch.desk_service.domain.service.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/desks")
@RequiredArgsConstructor
public class DeskController {
    private final DeskService deskService;
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Desk>> getAllDesks() {
        return ResponseEntity.ok(deskService.getAllDesks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Desk> getDeskById(@PathVariable String id) {
        return deskService.getDeskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/floor/{floor}")
    public ResponseEntity<List<Desk>> getDesksByFloor(@PathVariable String floor) {
        return ResponseEntity.ok(deskService.getDesksByFloor(floor));
    }

    @GetMapping("/building/{building}")
    public ResponseEntity<List<Desk>> getDesksByBuilding(@PathVariable String building) {
        return ResponseEntity.ok(deskService.getDesksByBuilding(building));
    }

    @PostMapping
    public ResponseEntity<Desk> createDesk(@RequestBody Desk desk) {
        return new ResponseEntity<>(deskService.createDesk(desk), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Desk> updateDesk(@PathVariable String id, @RequestBody Desk desk) {
        if (!id.equals(desk.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(deskService.updateDesk(desk));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesk(@PathVariable String id) {
        deskService.deleteDesk(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{deskId}/reservations")
    public ResponseEntity<List<Reservation>> getDeskReservations(
            @PathVariable String deskId,
            @RequestParam String start,
            @RequestParam String end) {
        try {
            LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);

            List<Reservation> reservations = reservationService.getReservationsByDeskAndDateRange(deskId, startDate, endDate);
            return ResponseEntity.ok(reservations);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reservations/current-month")
    public ResponseEntity<List<Reservation>> getCurrentMonthReservations() {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);
        LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        List<Reservation> reservations = reservationService.getReservationsByDateRange(startOfMonth, endOfMonth);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/reservation/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.createReservation(reservation), HttpStatus.CREATED);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        if (!id.equals(reservation.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable String id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}