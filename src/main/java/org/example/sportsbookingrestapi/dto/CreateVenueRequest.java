package org.example.sportsbookingrestapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVenueRequest(

        @NotBlank(message = "Venue name is required")
        String venueName,

        @NotBlank(message = "Venue location is required")
        String location,

        @NotNull(message = "Sport ID is required")
        @Min(value = 1, message = "Sport ID must be a valid positive number")
        Integer sportId
) {}
