package com.uditagarwal.cabbooking.controllers;

import com.uditagarwal.cabbooking.database.RidersManager;
import com.uditagarwal.cabbooking.database.TripsManager;
import com.uditagarwal.cabbooking.model.Location;
import com.uditagarwal.cabbooking.model.Rider;
import com.uditagarwal.cabbooking.model.Trip;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RidersController {
    private final RidersManager ridersManager;
    private final TripsManager tripsManager;

    public RidersController(RidersManager ridersManager, TripsManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @PostMapping(value = "/register/rider")
    public ResponseEntity registerRider(final String riderId, final String riderName) {
        ridersManager.createRider(new Rider(riderId, riderName));
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/book")
    public ResponseEntity book
            (
                    final String riderId,
                    final Double sourceX,
                    final Double sourceY,
                    final Double destX,
                    final Double destY
            ) {
        tripsManager.createTrip(
                ridersManager.getRider(riderId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/book")
    public ResponseEntity fetchHistory(final String riderId) {
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok(trips);
    }
}
