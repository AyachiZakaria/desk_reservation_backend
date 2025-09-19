package sofrecom.onetouch.desk_service.infrastructure.repository;

import org.springframework.stereotype.Component;
import sofrecom.onetouch.desk_service.domain.model.Desk;
import sofrecom.onetouch.desk_service.infrastructure.api.dto.DeskEntity;

@Component
public class DeskMapper {

    public Desk toDomain(DeskEntity entity) {
        if (entity == null) return null;

        return Desk.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .available(entity.isAvailable())
                .status(Desk.DeskStatus.valueOf(entity.getStatus()))
                .location(toLocationDomain(entity.getLocation()))
                .build();
    }

    private Desk.Location toLocationDomain(DeskEntity.LocationEntity locationEntity) {
        if (locationEntity == null) return null;

        return Desk.Location.builder()
                .x(locationEntity.getX())
                .y(locationEntity.getY())
                .floor(locationEntity.getFloor())
                .building(locationEntity.getBuilding())
                .build();
    }

    public DeskEntity toEntity(Desk domain) {
        if (domain == null) return null;

        return DeskEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .available(domain.isAvailable())
                .status(domain.getStatus().name())
                .location(toLocationEntity(domain.getLocation()))
                .build();
    }

    private DeskEntity.LocationEntity toLocationEntity(Desk.Location location) {
        if (location == null) return null;

        return DeskEntity.LocationEntity.builder()
                .x(location.getX())
                .y(location.getY())
                .floor(location.getFloor())
                .building(location.getBuilding())
                .build();
    }
}