package org.example.sportsbookingrestapi.service;

import org.example.sportsbookingrestapi.dto.VenueAvailabilityResponse;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.entity.BookingStatus;
import org.example.sportsbookingrestapi.entity.Slot;
import org.example.sportsbookingrestapi.entity.Venue;
import org.example.sportsbookingrestapi.repository.BookingRepository;
import org.example.sportsbookingrestapi.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AvailabilityService {

    private final SlotRepository slotRepository;
    private final BookingRepository bookingRepository;

    public List<VenueAvailabilityResponse> getAvailableVenues(
            int sportId,
            LocalTime startTime,
            LocalTime endTime
    ) {
        List<Slot> slots =
                slotRepository.findAvailableSlots(
                        sportId, startTime, endTime);

        return slots.stream()
                .filter(slot ->
                        !bookingRepository.existsBySlot_SlotIdAndStatus(
                                slot.getSlotId(), BookingStatus.BOOKED))
                .map(slot -> slot.getVenue())
                .distinct()
                .map(v -> new VenueAvailabilityResponse(
                        v.getVenueId(),
                        v.getVenueName(),
                        v.getLocation(),
                        v.getSportsId()
                ))
                .toList();
    }
}

