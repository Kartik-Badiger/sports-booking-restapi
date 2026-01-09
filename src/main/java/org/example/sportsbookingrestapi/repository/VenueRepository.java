package org.example.sportsbookingrestapi.repository;

import org.example.sportsbookingrestapi.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
