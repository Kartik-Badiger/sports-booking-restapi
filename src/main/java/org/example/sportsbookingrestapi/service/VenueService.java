package org.example.sportsbookingrestapi.service;

import lombok.RequiredArgsConstructor;
import org.example.sportsbookingrestapi.dto.CreateVenueRequest;
import org.example.sportsbookingrestapi.entity.Venue;
import org.example.sportsbookingrestapi.exception.VenueNotFoundException;
import org.example.sportsbookingrestapi.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public Venue createVenue(CreateVenueRequest request) {
        Venue venue = new Venue();
        venue.setVenueName(request.venueName());
        venue.setLocation(request.location());
        venue.setSportsId(request.sportId());

        return venueRepository.save(venue);
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new VenueNotFoundException(id));
    }

    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new VenueNotFoundException(id);
        }
        venueRepository.deleteById(id);
    }
}
