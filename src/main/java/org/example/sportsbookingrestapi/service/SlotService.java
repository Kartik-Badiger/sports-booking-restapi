package org.example.sportsbookingrestapi.service;

import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.dto.CreateSlotRequest;
import org.example.sportsbookingrestapi.dto.UpdateSlotRequest;
import org.example.sportsbookingrestapi.entity.Slot;
import org.example.sportsbookingrestapi.entity.Venue;
import org.example.sportsbookingrestapi.exception.SlotOverlapException;
import org.example.sportsbookingrestapi.exception.VenueNotFoundException;
import org.example.sportsbookingrestapi.repository.SlotRepository;
import org.example.sportsbookingrestapi.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;
    private final VenueRepository venueRepository;

    public Slot createSlot(CreateSlotRequest request) {

        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() -> new VenueNotFoundException(request.venueId()));

        boolean overlapExists =
                slotRepository.existsByVenue_VenueIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        venue.getVenueId(),
                        request.endTime(),
                        request.startTime()
                );

        if (overlapExists) {
            throw new SlotOverlapException();
        }

        Slot slot = new Slot();
        slot.setVenue(venue);
        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());
        slot.setPrice(request.price());

        return slotRepository.save(slot);
    }

    public Slot updateSlot(Long slotId, UpdateSlotRequest request) {

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));

        boolean overlapExists =
                slotRepository.existsByVenue_VenueIdAndSlotIdNotAndStartTimeLessThanAndEndTimeGreaterThan(
                        slot.getVenue().getVenueId(),
                        slotId,
                        request.endTime(),
                        request.startTime()
                );

        if (overlapExists) {
            throw new SlotOverlapException();
        }

        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());
        slot.setPrice(request.price());

        return slotRepository.save(slot);
    }

    public Slot getSlotById(Long slotId) {
        return slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + slotId));
    }

    public List<Slot> getSlotsByVenue(Long venueId) {
        return slotRepository.findByVenue_VenueId(venueId);
    }

    public void deleteSlot(Long slotId) {
        if (!slotRepository.existsById(slotId)) {
            throw new RuntimeException("Slot not found with id: " + slotId);
        }
        slotRepository.deleteById(slotId);
    }

    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
}
