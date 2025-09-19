package sofrecom.onetouch.desk_service.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sofrecom.onetouch.desk_service.infrastructure.api.dto.ReservationEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SpringDataMongoReservationRepository extends MongoRepository<ReservationEntity, String> {
    List<ReservationEntity> findByUserId(String userId);
    List<ReservationEntity> findByDeskId(String deskId);
    List<ReservationEntity> findByStartDateBetween(LocalDate start, LocalDate end);
}