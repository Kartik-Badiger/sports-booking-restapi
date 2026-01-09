package org.example.sportsbookingrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.dto.VenueAvailabilityResponse;
import org.example.sportsbookingrestapi.service.AvailabilityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping("/available")
    public ResponseEntity<List<VenueAvailabilityResponse>> getAvailableVenues(
            @RequestParam int sportId,

            @RequestParam
            @DateTimeFormat(pattern = "HH:mm")
            LocalTime startTime,

            @RequestParam
            @DateTimeFormat(pattern = "HH:mm")
            LocalTime endTime
    ) {
        return ResponseEntity.ok(
                availabilityService.getAvailableVenues(
                        sportId, startTime, endTime)
        );
    }
}
