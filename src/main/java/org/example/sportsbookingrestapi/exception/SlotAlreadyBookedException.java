package org.example.sportsbookingrestapi.exception;

public class SlotAlreadyBookedException extends RuntimeException {
    public SlotAlreadyBookedException(Long slotId) {
        super("Slot already booked for slotId: " + slotId);
    }

}
