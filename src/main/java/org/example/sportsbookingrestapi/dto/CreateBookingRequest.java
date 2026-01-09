package org.example.sportsbookingrestapi.dto;

import jakarta.validation.constraints.NotNull;

public record CreateBookingRequest(
        @NotNull Long slotId
) {}

