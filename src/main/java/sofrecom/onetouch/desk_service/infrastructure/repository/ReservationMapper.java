package sofrecom.onetouch.desk_service.infrastructure.repository;

import org.springframework.stereotype.Component;
import sofrecom.onetouch.desk_service.domain.model.Reservation;
import sofrecom.onetouch.desk_service.infrastructure.api.dto.ReservationEntity;

@Component
public class ReservationMapper {

    public Reservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;

        return Reservation.builder()
                .id(entity.getId())
                .deskId(entity.getDeskId())
                .userId(entity.getUserId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .status(Reservation.ReservationStatus.valueOf(entity.getStatus()))
                .build();
    }

    public ReservationEntity toEntity(Reservation domain) {
        if (domain == null) return null;

        return ReservationEntity.builder()
                .id(domain.getId())
                .deskId(domain.getDeskId())
                .userId(domain.getUserId())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .status(domain.getStatus().name())
                .build();
    }
}