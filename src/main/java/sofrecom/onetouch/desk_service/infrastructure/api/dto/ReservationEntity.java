package sofrecom.onetouch.desk_service.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reservations")
public class ReservationEntity {
    @Id
    private String id;
    private String deskId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}