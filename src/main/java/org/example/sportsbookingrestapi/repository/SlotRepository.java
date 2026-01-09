package org.example.sportsbookingrestapi.repository;

import org.example.sportsbookingrestapi.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    boolean existsByVenue_VenueIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long venueId,
            LocalTime endTime,
            LocalTime startTime
    );

    List<Slot> findByVenue_VenueId(Long venueId);

    boolean existsByVenue_VenueIdAndSlotIdNotAndStartTimeLessThanAndEndTimeGreaterThan(
            Long venueId,
            Long slotId,
            LocalTime endTime,
            LocalTime startTime
    );

    List<Slot> findByVenue_SportsIdAndStartTimeLessThanAndEndTimeGreaterThan(
            int sportsId,
            LocalTime endTime,
            LocalTime startTime
    );

    @Query("""
    SELECT s FROM Slot s
    JOIN s.venue v
    WHERE v.sportsId = :sportId
      AND s.startTime < :endTime
      AND s.endTime > :startTime
""")
    List<Slot> findAvailableSlots(
            @Param("sportId") int sportId,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

}