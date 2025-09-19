package sofrecom.onetouch.desk_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String id;
    private String deskId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public enum ReservationStatus {
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }
}