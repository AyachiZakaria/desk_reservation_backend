package sofrecom.onetouch.desk_service.infrastructure.api.dto;

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
@Document(collection = "desks")
public class DeskEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private LocationEntity location;
    private boolean available;
    private String status;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LocationEntity {
        private double x;
        private double y;
        private String floor;
        private String building;
    }
}