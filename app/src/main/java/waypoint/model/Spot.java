package waypoint.model;

import java.util.Objects;

public final class Spot {
    private final SpotId id;
    private VehicleId occupant;

    public Spot(SpotId id) {
        this.id = Objects.requireNonNull(id, "id");
    }

    public boolean tryAssign(VehicleId v) {
        Objects.requireNonNull(v, "vehicle");
        if (occupant != null) {
            return false;
        }
        occupant = v;
        return true;
    }

    public void release(VehicleId v) {
        if (occupant != null && occupant.equals(v)) {
            occupant = null;
        }
    }

    public boolean isFree() {
        return occupant == null;
    }

    public SpotId id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Spot s) && s.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}