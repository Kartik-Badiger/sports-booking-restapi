package org.example.sportsbookingrestapi.repository;

import org.example.sportsbookingrestapi.entity.Booking;
import org.example.sportsbookingrestapi.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsBySlot_SlotIdAndStatus(Long slotId, BookingStatus status);
}

