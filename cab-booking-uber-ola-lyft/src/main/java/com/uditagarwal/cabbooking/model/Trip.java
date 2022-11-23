package com.uditagarwal.cabbooking.model;

import lombok.NonNull;
import lombok.ToString;

import static com.uditagarwal.cabbooking.model.TripStatus.FINISHED;
import static com.uditagarwal.cabbooking.model.TripStatus.IN_PROGRESS;

enum TripStatus {
    IN_PROGRESS,
    FINISHED
}

@ToString
public class Trip {
    private final Rider rider;
    private final Cab cab;
    private TripStatus status;
    private final Double price;
    // pickup location
    private final Location fromPoint;
    // where to
    private final Location toPoint;

    public Trip(
            @NonNull final Rider rider,
            @NonNull final Cab cab,
            @NonNull final Double price,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.status = IN_PROGRESS;
    }

    public void endTrip() {
        this.status = FINISHED;
    }
}
