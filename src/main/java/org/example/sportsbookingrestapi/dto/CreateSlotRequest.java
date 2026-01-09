package org.example.sportsbookingrestapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record CreateSlotRequest(

        @NotNull(message = "Venue ID is required")
        Long venueId,

        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @NotNull(message = "End time is required")
        LocalTime endTime,

        @Positive(message = "Price must be positive")
        double price
) {}
