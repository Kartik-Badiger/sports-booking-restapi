package org.example.sportsbookingrestapi.dto;

public record VenueAvailabilityResponse(
        Long venueId,
        String venueName,
        String location,
        int sportsId
) {}

