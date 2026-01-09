package org.example.sportsbookingrestapi.exception;

public class SlotOverlapException extends RuntimeException {

    public SlotOverlapException() {
        super("Slot overlaps with an existing slot for this venue");
    }
}
