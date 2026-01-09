package org.example.sportsbookingrestapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.dto.CreateSlotRequest;
import org.example.sportsbookingrestapi.dto.UpdateSlotRequest;
import org.example.sportsbookingrestapi.entity.Slot;
import org.example.sportsbookingrestapi.service.SlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
@RequiredArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @PostMapping
    public ResponseEntity<Slot> createSlot(
            @Valid @RequestBody CreateSlotRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(slotService.createSlot(request));
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<List<Slot>> getSlotsByVenue(
            @PathVariable Long venueId) {

        return ResponseEntity.ok(slotService.getSlotsByVenue(venueId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Slot> getSlot(@PathVariable Long id) {
        return ResponseEntity.ok(slotService.getSlotById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Slot> updateSlot(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSlotRequest request) {

        return ResponseEntity.ok(slotService.updateSlot(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Long id) {
        slotService.deleteSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Slot>> getAllSlots() {
        return ResponseEntity.ok(slotService.getAllSlots());
    }
}
