package waypoint.model;

record TicketId(String value) {
    TicketId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("TicketId cannot be null or blank");
        }
    }
}

record SpotId(String value) {
    SpotId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SpotId cannot be null or blank");
        }
    }
}

record VehicleId(String value) {
    VehicleId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("VehicleId cannot be null or blank");
        }
    }
}