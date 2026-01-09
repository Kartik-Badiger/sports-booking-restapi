package org.example.sportsbookingrestapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.dto.CreateVenueRequest;
import org.example.sportsbookingrestapi.entity.Venue;
import org.example.sportsbookingrestapi.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<Venue> createVenue(
            @Valid @RequestBody CreateVenueRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(venueService.createVenue(request));
    }

    @GetMapping
    public ResponseEntity<List<Venue>> getVenues() {
        return ResponseEntity.ok(venueService.getAllVenues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable Long id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }
}