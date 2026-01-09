package org.example.sportsbookingrestapi.service;

import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.entity.Booking;
import org.example.sportsbookingrestapi.entity.BookingStatus;
import org.example.sportsbookingrestapi.entity.Slot;
import org.example.sportsbookingrestapi.exception.SlotAlreadyBookedException;
import org.example.sportsbookingrestapi.repository.BookingRepository;
import org.example.sportsbookingrestapi.repository.SlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;


    @Transactional
    public Booking createBooking(Long slotId) {

        if (bookingRepository.existsBySlot_SlotIdAndStatus(
                slotId, BookingStatus.BOOKED)) {

            throw new SlotAlreadyBookedException(slotId);
        }

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() ->
                        new RuntimeException("Slot not found with id: " + slotId));

        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setStatus(BookingStatus.BOOKED);

        return bookingRepository.save(booking);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found with id: " + bookingId));

        booking.setStatus(BookingStatus.BOOKED);
        bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
