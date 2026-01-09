Sports Venue Booking Backend

A robust RESTful backend service built with Spring Boot to manage sports venues, time slots, and real-time bookings. This system simulates a real-world booking platform with strict concurrency controls to prevent double bookings and overlapping schedules.

Tech Stack

Framework: Java 17 plus Spring Boot 4.1
Database: MySQL 8.0
ORM: Spring Data JPA and Hibernate
Containerization: Docker and Docker Compose
API Protocol: REST JSON
Core Functionalities

1. Venue and Slot Management
   Venue Control: Full CRUD operations for sports venues.
   Smart Slotting: Add time slots with overlap prevention logic per venue.
   Sport Integration: Validates sports against the mandatory external sports API.

2. Availability and Search
   Query venues based on specific sports and time ranges.
   Logic ensures only venues with active available slots are returned in the search results.

3. Booking Engine
   Concurrency Guard: Prevents double booking of the same slot.
   Lifecycle Management: View booking history or cancel existing reservations.
   Instant Recovery: Cancelling a booking immediately frees the slot for re-booking while preserving the historical record.
   Database Design

Venues Table
Columns: venue_id, venue_name, location, sports_id
Description: Stores venue metadata and sport association.

Slots Table
Columns: slot_id, venue_id, start_time, end_time, price
Description: Defined time blocks. Constraints prevent overlaps.

Bookings Table
Columns: booking_id, slot_id, status
Description: Tracks BOOKED or CANCELLED states.

Design Note: slot_id in the Bookings table is not unique. This allows the system to track multiple booking attempts and cancellations for a single slot over time.
API Documentation

Venue Endpoints
POST   /venues                     Create a new sports venue
GET    /venues                     List all registered venues
POST   /venues/id/slots            Add a time slot to a specific venue
GET    /venues/available           Filter venues by sportId, startTime, and endTime

Booking Endpoints
POST   /bookings                   Book a specific slot
GET    /bookings                   Retrieve all booking records
PUT    /bookings/id/cancel         Cancel an active booking and free the slot
Getting Started

Prerequisites
Docker and Docker Compose installed.

Deployment
Run the following command in the project root to spin up the MySQL database and the Spring Boot application:

docker-compose up --build

The services will be available at:
Backend API: http://localhost:8080
MySQL Database: localhost:3306
Sample API Requests

# 1. Create Venue
curl -X POST http://localhost:8080/venues \
-H "Content-Type: application/json" \
-d '{
"venueName": "City Sports Arena",
"location": "Bangalore",
"sportsId": 1
}'

# 2. Get All Venues
curl http://localhost:8080/venues

# 3. Create Slot for a Venue
curl -X POST http://localhost:8080/venues/1/slots \
-H "Content-Type: application/json" \
-d '{
"startTime": "06:00",
"endTime": "07:00",
"price": 500
}'

# 4. Get Available Venues
curl "http://localhost:8080/venues/available?sportId=1&startTime=06:00&endTime=07:00"

# 5. Create Booking
curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{
"slotId": 1
}'

# 6. Get All Bookings
curl http://localhost:8080/bookings

# 7. Cancel Booking
curl -X PUT http://localhost:8080/bookings/1/cancel

# 8. Re-book Slot After Cancellation
curl -X POST http://localhost:8080/bookings \
-H "Content-Type: application/json" \
-d '{
"slotId": 1
}'

# 9. Overlapping Slot (Expected Failure)
curl -X POST http://localhost:8080/venues/1/slots \
-H "Content-Type: application/json" \
-d '{
"startTime": "06:30",
"endTime": "07:30",
"price": 600
}'