package sofrecom.onetouch.desk_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Desk {
    private String id;
    private String name;
    private String description;
    private Location location;
    private boolean available;
    private DeskStatus status;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location {
        private double x;
        private double y;
        private String floor;
        private String building;
    }

    public enum DeskStatus {
        AVAILABLE,
        RESERVED,
        MAINTENANCE,
        ACTIVE
    }
}
