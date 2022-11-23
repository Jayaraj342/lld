package com.uditagarwal.cabbooking.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Cab {
    String id;
    String driverName;

    @Setter
    Trip currentTrip;
    @Setter
    Location currentLocation;
    @Setter
    Boolean isAvailable;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driverName='" + driverName + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cab)) return false;
        Cab cab = (Cab) o;
        return getId().equals(cab.getId()) && getDriverName().equals(cab.getDriverName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDriverName());
    }
}
